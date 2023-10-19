package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTownDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    private Path townFilePath = Path.of("src", "main", "resources", "files", "json", "towns.json");

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder()
                .create();
        this.mapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    public String readTownsFileContent() throws IOException {
        return Files.readString(townFilePath);
    }

    public String importTowns() throws IOException {
        String json = readTownsFileContent();

        List<String> result = new ArrayList<>();
        ImportTownDTO[] importTownDTO = this.gson.fromJson(json, ImportTownDTO[].class);
        for (ImportTownDTO townDTO : importTownDTO) {
            Set<ConstraintViolation<ImportTownDTO>> validationError = this.validator.validate(townDTO);
            if(validationError.isEmpty()) {
                Optional<Town> optTown = this.townRepository.findByName(townDTO.getTownName());
                if(optTown.isEmpty() && townDTO.getPopulation() > 1){
                    Town town = this.mapper.map(townDTO, Town.class);
                    this.townRepository.save(town);
                    result.add(String.format("Successfully imported town %s - %d", town.getName(), town.getPopulation()));
                }else {
                    result.add("Invalid town!");
                }
            }else {
                result.add("Invalid town!");
            }
        }

        return String.join(System.lineSeparator(), result);
    }
}
