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
                    LocalDate.now(),
                    "Leroy",
                    LocalDate.of(2000, JANUARY, 5),
                    "Leroy@Jenkins.com",
                    "Netherlands",
                    false,
                    "Leroy Jenkins",
                    "trance"
            );

            Person belle = new Person(
                    LocalDate.now(),
                    "Kat Gunn",
                    LocalDate.of(2002, JULY, 16),
                    "yametekudasai@ara-ara.com",
                    "America",
                    true,
                    "Mystik",
                    "J-pop"
            );

            Person michael = new Person(
                    LocalDate.now(),
                    "Michael Blackson",
                    LocalDate.of(2002, JULY, 16),
                    "m.blackson@gmail.com",
                    "America",
                    true,
                    "INooDiWee",
                    "RnB"
            );

            Person mj = new Person(
                    LocalDate.now(),
                    "Michael Jackson",
                    LocalDate.of(2002, JULY, 16),
                    "m.jackson@jackson-five.com",
                    "America",
                    true,
                    "Michael Jackson",
                    "pop"
            );

            Person rhcp = new Person(
                    LocalDate.now(),
                    "Red Hot chili Peppers",
                    LocalDate.of(2002, JULY, 16),
                    "the-band@rhcp.com",
                    "America",
                    true,
                    "Red Hot Chili Peppers",
                    "Rock"
            );

            Person mike = new Person(
                    LocalDate.now(),
                    "Mike Wazowsky",
                    LocalDate.of(2002, JULY, 16),
                    "m.wazowsky@monsters-inc.com",
                    "Monstropolis",
                    false,
                    "",
                    ""
            );

            Person jack = new Person(
                    LocalDate.now(),
                    "Jack Sparrow",
                    LocalDate.of(2002, JULY, 16),
                    "sparrow@scarvee.sea",
                    "the Black Pearl",
                    false,
                    "",
                    ""
            );

            Person house = new Person(
                    LocalDate.now(),
                    "Gregory House",
                    LocalDate.of(2002, JULY, 16),
                    "house@md.com",
                    "America",
                    true,
                    "Hugh Laurie",
                    "Jazz"
            );

            Person rosario = new Person(
                    LocalDate.now(),
                    "Rosario Dawson",
                    LocalDate.of(2002, JULY, 16),
                    "r.dawson@gmail.com",
                    "America",
                    true,
                    "Rosario Dawson",
                    "Ballades"
            );

            repository.saveAll(
                    List.of(leroy, belle, michael, mj, rhcp, mike, jack, house, rosario)
            );
        };
    }
}
