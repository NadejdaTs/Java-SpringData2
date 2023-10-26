package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentDTO;
import softuni.exam.models.dto.ImportApartmentDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    @Autowired
    private ModelMapper mapper;

    private Path apartmentFilePath = Path.of("src", "main", "resources", "files", "xml", "apartments.xml");

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(apartmentFilePath);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ImportApartmentDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportApartmentDTO apartmentDTOs = (ImportApartmentDTO) unmarshaller.unmarshal(
                new FileReader(apartmentFilePath.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (ApartmentDTO apartmentDTO : apartmentDTOs.getApartments()) {
            if(apartmentDTO.validate()) {
                Apartment apartment = this.mapper.map(apartmentDTO, Apartment.class);
                Optional<Town> optTown = this.townRepository.findByName(apartmentDTO.getTown());

                Optional<Apartment> optApartment = this.apartmentRepository
                        .findByTownNameAndArea(optTown.get().getName(), apartmentDTO.getArea());
                if(optApartment.isEmpty()){
                    apartment.setTown(optTown.get());
                    this.apartmentRepository.save(apartment);
                    result.add(String.format("Successfully imported apartment %s - %.2f",
                            apartment.getApartmentType().toString(), apartment.getArea()));
                }else {
                    result.add("Invalid apartment!");
                }
            }else{
                result.add("Invalid apartment!");
            }
        }

        return String.join(System.lineSeparator(), result);
    }
}
