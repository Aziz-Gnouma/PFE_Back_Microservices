package com.example.cvai.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Endpoint que vous voulez autoriser
                .allowedOrigins("http://localhost:4200") // Autoriser les requêtes provenant de cette URL (votre frontend Angular)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
                .allowedHeaders("*"); // Tous les headers autorisés
    }
}
