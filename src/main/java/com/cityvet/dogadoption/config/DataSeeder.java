package com.cityvet.dogadoption.config;

import com.cityvet.dogadoption.entity.User;
import com.cityvet.dogadoption.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String adminUsername = "admin";

        userRepository.findByUsername(adminUsername).orElseGet(() -> {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            return userRepository.save(admin);
        });
    }
}