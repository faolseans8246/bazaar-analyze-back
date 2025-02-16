package org.example.bazaaranalyze.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://bazaar-analyze-front.vercel.app")  // Frontend uchun manzil
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Foydalanishga ruxsat berilgan metodlar
                .allowedHeaders("*")  // Barcha so'rov sarlavhalariga ruxsat
                .allowCredentials(true);
    }
}