package dev.sarakutleshi.recipeapp.repositories.initializer;

import dev.sarakutleshi.recipeapp.models.User;
import dev.sarakutleshi.recipeapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDataInitializer implements Runnable {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder1;
    // password encoder

    @Autowired
    public UserDataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.passwordEncoder1 = passwordEncoder1;
    }

    @Override
    public void run() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setName("Sara");
            user1.setSurname("Kutleshi");
            user1.setEmail("sara.kutleshi@gmail.com");
            user1.setUsername("sarakutleshi");
            user1.setBirthdate(LocalDate.of(2000, 1, 1));
            user1.setPassword("Admin123"); //password.encode("123123123")
            userRepository.save(user1);
        }
    }
}
