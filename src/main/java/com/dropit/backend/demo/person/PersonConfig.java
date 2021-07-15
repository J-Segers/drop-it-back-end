package com.dropit.backend.demo.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person leroy = new Person(
                    "Leroy",
                    "Leroy Jenkins",
                    "Leroy@Jenkins.com",
                    "Netherlands",
                    LocalDate.of(2000, JANUARY, 5)

            );

            Person belle = new Person(
                    "Belle",
                    "Belle Delphine",
                    "yametekudasai@ara-ara.com",
                    "idiot ville",
                    LocalDate.of(2002, JULY, 16)
            );

            repository.saveAll(
                    List.of(leroy, belle)
            );
        };
    }
}
