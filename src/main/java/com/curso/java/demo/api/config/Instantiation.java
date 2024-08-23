package com.curso.java.demo.api.config;

import com.curso.java.demo.api.domains.User;
import com.curso.java.demo.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {

        userRepo.deleteAll();

        User u1 = new User(null, "Maria Brown", "maria@gmail.com");
        User u2 = new User(null, "Alex Green", "alex@gmail.com");
        User u3 = new User(null, "Bob Grey", "bob@gmail.com");

        userRepo.saveAll(Arrays.asList(u1,u2,u3));
    }
}
