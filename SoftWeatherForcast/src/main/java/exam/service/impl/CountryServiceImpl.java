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
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private Gson gson;

    @Autowired
    private Validator validator;
    private Path countryFilePath = Path.of("src", "main", "resources", "files", "json", "countries.json");

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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
//            boolean isValidCountry = countryDTO.validate();
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
