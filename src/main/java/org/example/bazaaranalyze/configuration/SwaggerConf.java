package org.example.bazaaranalyze.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(
        info = @Info(

                contact = @Contact(
                        name = "Finance application"
//                        email = "feruzbekhamrayev2002@gmail.com"
                ),
                title = "Bazaar analyze web application",
                version = "1.0.0",
                description = "Moliyaviy bozor analizini shakllantirish dasturini ishlab chiqish"
        )

//        security = @SecurityRequirement(
//                name = "OAuth2.0"
//        )
)
public class SwaggerConf {
}
