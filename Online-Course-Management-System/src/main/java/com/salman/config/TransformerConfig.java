package com.salman.config;

import com.salman.transformer.StudentTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformerConfig {

    @Bean
    public StudentTransformer studentTransformer() {
        return new StudentTransformer();
    }
}
