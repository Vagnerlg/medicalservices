package vagnerlg.com.github.medicalservices.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDoc {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Medical serivces")
                .version("v1")
                .contact(new Contact()
                    .email("vlgonalves@gmail.com")
                    .name("Vagner Gonçalves")));
    }
}
