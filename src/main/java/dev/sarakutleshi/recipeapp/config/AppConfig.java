package dev.sarakutleshi.recipeapp.config;

import dev.sarakutleshi.recipeapp.repositories.initializer.UserDataInitializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApplicationRunner initializer(UserDataInitializer initializer) {
        return args -> initializer.run();
    }
}
