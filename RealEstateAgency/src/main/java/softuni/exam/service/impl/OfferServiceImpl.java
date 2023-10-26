package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.*;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;
    @Autowired
    private ModelMapper mapper;

    private Path offerFilePath = Path.of("src", "main", "resources", "files", "xml", "offers.xml");

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository) {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(offerFilePath);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ImportOffersDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportOffersDTO offerDTOs = (ImportOffersDTO) unmarshaller.unmarshal(new FileReader(offerFilePath.toAbsolutePath().toString()));

        Converter<String, LocalDate> toLocalDate = s -> s.getSource() == null
                        ? null
                        : LocalDate.parse(s.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        mapper.addConverter(toLocalDate);

        List<String> result = new ArrayList<>();
        for (ImportOfferDTO offerDTO : offerDTOs.getOffers()) {
            Offer offer = new Offer();
            ApartmentIdDTO apartmentIdDTO = offerDTO.getId().stream()
                    .filter(a -> a.getId() != null)
                    .findFirst()
                    .orElse(null);
            AgentDTO agentDTO = offerDTO.getAgent().stream()
                    .filter(a -> a.getName() != null)
                    .findFirst()
                    .orElse(null);
            if (apartmentIdDTO == null || agentDTO == null) {
                result.add("Invalid offer!");
            } else {
                Optional<Apartment> apartment = this.apartmentRepository.findById(apartmentIdDTO.getId());
                Optional<Agent> agent = this.agentRepository.findByFirstName(agentDTO.getName());

                if(apartment.isEmpty() || agent.isEmpty() || offerDTO.getPrice().compareTo(BigDecimal.valueOf(0)) != 1){
                    result.add("Invalid offer!");
                }else{
                    offer.setApartment(apartment.get());
                    offer.setAgent(agent.get());

                    LocalDate currDate = LocalDate.parse(offerDTO.getPublishedOn(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    offer.setPublishedOn(currDate);
                    offer.setPrice(offerDTO.getPrice());

                    this.offerRepository.save(offer);
                    result.add(String.format("Successfully imported offer %.2f", offer.getPrice()));
                }
            }
        }

        return String.join(System.lineSeparator(), result);
    }

    @Override
    public String exportOffers() {
        List<Offer> offers = this.offerRepository.findAllByApartmentTypeOrderByAreaAndPrice(ApartmentType.three_rooms);
        StringBuilder sb = new StringBuilder();
        offers.forEach(offer -> sb.append(offer.toString()));

        return sb.toString().trim();
    }
}
