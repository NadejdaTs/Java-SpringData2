package examPrep.service.impl;

import examPrep.model.ImportTownDTO;
import examPrep.model.ImportTownsDTO;
import examPrep.model.Town;
import examPrep.repository.TownRepository;
import examPrep.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Path xmlPath = Path.of("src", "main", "resources", "files", "xml", "towns.xml");
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(xmlPath));
    }

    @Override
    public String importTowns() throws JAXBException, IOException {
        List<String> result = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(ImportTownsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportTownsDTO towns = (ImportTownsDTO) unmarshaller.unmarshal(new FileReader(xmlPath.toAbsolutePath().toString()));

        for (ImportTownDTO t : towns.getTowns()) {
            if (t.isValid()) {
                Optional<Town> optTown = this.townRepository.findByName(t.getName());
                if (optTown.isPresent()) {
                    result.add("Town already exist");
                } else {
                    Town toPersist = this.mapper.map(t, Town.class);
                    this.townRepository.save(toPersist);
                    result.add("Saved Town " + t.getName());
                }
            } else {
                result.add("Invalid Town");
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
