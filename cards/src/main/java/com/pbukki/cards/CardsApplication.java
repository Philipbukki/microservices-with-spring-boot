package com.pbukki.cards;

import com.pbukki.cards.dto.CardsContactDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactDto.class})
@OpenAPIDefinition(
        info = @Info(
                title="Cards Microservice api Documentation",
                description = "Bukki's Card's Microservice api Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Philip Bukki",
                        email = "phil.bukki@gmail.com",
                        url = "phil_b.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://apache.org/"

                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Learn more about springdoc-openapi",
                url = "https://springdoc.org/"
        )
)
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
