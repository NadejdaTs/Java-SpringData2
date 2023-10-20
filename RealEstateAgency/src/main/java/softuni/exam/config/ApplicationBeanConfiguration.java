package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

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
