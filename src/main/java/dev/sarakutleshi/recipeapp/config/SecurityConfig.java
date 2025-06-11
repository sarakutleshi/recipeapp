package dev.sarakutleshi.recipeapp.config;

import dev.sarakutleshi.recipeapp.models.User;
import dev.sarakutleshi.recipeapp.repositories.UserRepository;
import dev.sarakutleshi.recipeapp.security.AppUserDetailsService;
import dev.sarakutleshi.recipeapp.security.JwtAuthenticationFilter;
import dev.sarakutleshi.recipeapp.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;

import static dev.sarakutleshi.recipeapp.models.Permission.*;
import static dev.sarakutleshi.recipeapp.models.Role.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/log-in").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/sign-up").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/log-in").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/recipes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/recipes/new").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()


                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())


                        .requestMatchers(HttpMethod.GET, "/api/v1/management/**")
                        .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/**")
                        .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/management/**")
                        .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**")
                        .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())


                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // cross-site request forgery
                .cors(cors -> {
                })
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        AppUserDetailsService appUserDetailsService = new AppUserDetailsService(repository);
        String email = "user@test.com";
        repository.findByEmail(email)
                .orElseGet(() -> {
                    var newUser = User.builder()
                            .name("User")
                            .username("user")
                            .surname("User")
                            .email(email)
                            .role(ADMIN)
                            .password(passwordEncoder().encode("password"))
                            .build();

                    return repository.save(newUser);
                });

        return appUserDetailsService;
    }
}