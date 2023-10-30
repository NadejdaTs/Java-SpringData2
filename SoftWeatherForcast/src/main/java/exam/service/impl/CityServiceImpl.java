package exam.service.impl;

import com.google.gson.Gson;
import exam.models.dto.ImportCityDTO;
import exam.models.entity.City;
import exam.models.entity.Country;
import exam.repository.CityRepository;
import exam.repository.CountryRepository;
import exam.service.CityService;
import jakarta.validation.ConstraintViolation;
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
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private ModelMapper mapper;
    private Gson gson;
    private Validator validator;
    private Path cityFilePath = Path.of("src", "main", "resources", "files", "json", "cities.json");

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, ModelMapper mapper, Gson gson, Validator validator) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validator = validator;
    }

    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    public String readCitiesFileContent() throws IOException {
        return Files.readString(cityFilePath);
    }

    public String importCities() throws IOException {
        ImportCityDTO[] importCityDTO = this.gson.fromJson(readCitiesFileContent(), ImportCityDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportCityDTO cityDTO : importCityDTO) {
            Set<ConstraintViolation<ImportCityDTO>> validatorError = this.validator.validate(cityDTO);
            Optional<City> optCity = this.cityRepository.findByCityName(cityDTO.getCityName());

            if(validatorError.isEmpty() && optCity.isEmpty()) {
                Optional<Country> optCountry = this.countryRepository.findById(cityDTO.getCountry());

                City city = this.mapper.map(cityDTO, City.class);
                city.setCountry(optCountry.get());

                this.cityRepository.save(city);
                result.add(String.format("Successfully imported city %s - %d",
                        city.getCityName(), city.getPopulation()));
            }else{
                result.add("Invalid city");
            }
        }

        return String.join(System.lineSeparator(), result);
    }
}
