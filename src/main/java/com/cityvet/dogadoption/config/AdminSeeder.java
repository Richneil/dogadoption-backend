package com.cityvet.dogadoption.config;

import com.cityvet.dogadoption.entity.User;
import com.cityvet.dogadoption.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        String adminUsername = "admin";
        String adminPassword = "admin123";

        User admin = userRepository.findByUsername(adminUsername).orElse(null);

        if (admin == null) {
            admin = new User();
            admin.setUsername(adminUsername);
        }

        admin.setRole("ADMIN");
        admin.setPassword(passwordEncoder.encode(adminPassword)); // ✅ BCrypt encoded

        userRepository.save(admin);
        System.out.println("✅ Admin ensured: username=admin password=admin123 role=ADMIN");
    }
}