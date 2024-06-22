package com.example.documentauth.controlle;

import com.example.documentauth.entity.QRCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public QRCodeGenerator qrCodeGenerator() {
        return new QRCodeGenerator();
    }
}
