package exam.service.impl;

import com.google.gson.Gson;
import exam.models.dto.ImportCountryDTO;
import exam.models.entity.Country;
import exam.repository.CountryRepository;
import exam.service.CountryService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private ModelMapper mapper;
    private Gson gson;
    private Validator validator;
    private Path countryFilePath = Path.of("src", "main", "resources", "files", "json", "countries.json");

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper mapper, Gson gson, Validator validator) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validator = validator;
    }

    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    public String readCountriesFromFile() throws IOException {
        return Files.readString(countryFilePath);
    }

    public String importCountries() throws IOException {
        ImportCountryDTO[] importCountryDTO = this.gson.fromJson(readCountriesFromFile(), ImportCountryDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportCountryDTO countryDTO : importCountryDTO) {
            Set<ConstraintViolation<ImportCountryDTO>> validatorError = this.validator.validate(countryDTO);
            Optional<Country> optCountry = this.countryRepository.findByCountryName(countryDTO.getCountryName());

            if(validatorError.isEmpty() && optCountry.isEmpty()) {
               Country country = this.mapper.map(countryDTO, Country.class);

               this.countryRepository.save(country);
               result.add(String.format("Successfully imported country %s - %s",
                       country.getCountryName(), country.getCurrency()));
            }else{
                result.add("Invalid country");
            }
        }

        return String.join(System.lineSeparator(), result);
    }
}
