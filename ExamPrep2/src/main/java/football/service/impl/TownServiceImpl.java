package football.service.impl;

import football.models.dto.ImportTownDTO;
import football.models.entity.Town;
import football.repository.TownRepository;
import football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Path townPathFile = Path.of("src", "main", "resources", "files", "json", "towns.json");

    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(townPathFile);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();
        ImportTownDTO[] importTownDTOS = this.gson.fromJson(json, ImportTownDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportTownDTO importTownDTO : importTownDTOS) {
            Set<ConstraintViolation<ImportTownDTO>> validationErrors = this.validator.validate(importTownDTO);
            if(validationErrors.isEmpty()){
                Optional<Town> optTown = this.townRepository.findByName(importTownDTO.getName());
                if(optTown.isEmpty()){
                    Town town = this.mapper.map(importTownDTO, Town.class);
                    this.townRepository.save(town);
                    result.add(String.format("Successfully imported Town %s - %d",
                            town.getName(), town.getPopulation()));
                }else{
                    result.add("Invalid town");
                }
            }else{
                result.add("Invalid town");
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
