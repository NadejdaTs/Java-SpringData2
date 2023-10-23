package exam.service.impl;

import exam.models.dto.ImportForecastDTO;
import exam.models.dto.ImportForecastsDTO;
import exam.models.entity.City;
import exam.models.entity.DayOfWeek;
import exam.models.entity.Forecast;
import exam.repository.CityRepository;
import exam.repository.ForecastRepository;
import exam.service.ForecastService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validator validator;

    private Path forecastFilePath = Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
    }

    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    public String readForecastsFromFile() throws IOException {
        return Files.readString(forecastFilePath);
    }

    public String importForecasts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ImportForecastsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportForecastsDTO forecastDTOs = (ImportForecastsDTO) unmarshaller.unmarshal(
                new FileReader(forecastFilePath.toAbsolutePath().toString()));

        Converter<String, LocalTime> toLocalTime = src -> src.getSource() == null
                ? LocalTime.of(0, 0, 0)
                : LocalTime.parse(src.getSource());

        mapper.addConverter(toLocalTime);
        TypeMap<ImportForecastDTO, Forecast> typeMap = this.mapper.createTypeMap(ImportForecastDTO.class, Forecast.class);

        List<String> result = new ArrayList<>();
        for (ImportForecastDTO forecastDTO : forecastDTOs.getForecasts()) {
            Set<ConstraintViolation<ImportForecastDTO>> validatorError = this.validator.validate(forecastDTO);
            if(validatorError.isEmpty()) {
                Optional<City> optCity = this.cityRepository.findById(forecastDTO.getCity());
                Optional<Forecast> optForecast = this.forecastRepository.findByDayOfWeekAndCityId(forecastDTO.getCity(), forecastDTO.getDayOfWeek());
                if(!optCity.isEmpty() && optForecast.isEmpty()){
                    TypeMap<ImportForecastDTO, Forecast> typeMapLocalDate = typeMap.addMappings(map ->
                            map.using(toLocalTime).map(ImportForecastDTO::getSunrise, Forecast::setSunrise));
                    typeMapLocalDate.addMappings(map ->
                            map.using(toLocalTime).map(ImportForecastDTO::getSunset, Forecast::setSunset));

                    Forecast forecast = this.mapper.map(forecastDTO, Forecast.class);
                    forecast.setCity(optCity.get());

                    this.forecastRepository.save(forecast);
                    result.add(String.format("Successfully import forecast %s - %.2f",
                            forecast.getDayOfWeek(), forecast.getMaxTemperature()));
                }else{
                    result.add("Invalid forecast");
                }
            }else{
                result.add("Invalid forecast");
            }
        }

        return String.join(System.lineSeparator(), result);
    }

    public String exportForecasts() {
        List<Forecast> forecasts = this.forecastRepository.findAllFromSundayAndCitiesPopulationOrdered(DayOfWeek.SUNDAY);
        StringBuilder sb = new StringBuilder();
        forecasts.forEach(forecast -> sb.append(forecast.toString()));

        return sb.toString().trim();
    }
}
