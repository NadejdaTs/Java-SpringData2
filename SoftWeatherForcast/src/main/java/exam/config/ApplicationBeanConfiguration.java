package exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.models.dto.ImportForecastDTO;
import exam.models.entity.Forecast;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalTime;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean(name = "default")
    @Primary
    public ModelMapper getMapper(){
        return new ModelMapper();
    }

    /*@Bean(name = "Alternative")
    public ModelMapper getAltMapper(){
        @Qualifier("Alternative") //in constructor parameter
        ModelMapper mapper = new ModelMapper();
        Converter<String, LocalTime> toLocalTime = src -> src.getSource() == null
                ? LocalTime.of(0, 0, 0)
                : LocalTime.parse(src.getSource());

        mapper.addConverter(toLocalTime);
        return mapper;
    }*/

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
    public Validator validator() {
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
}
