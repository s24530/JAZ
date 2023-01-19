package com.example.demo.manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class ManagerConfig {

    @Bean
    CommandLineRunner commandLine(ManagerRepository repository) {
        return args ->{
            Manager alicja = new Manager(
                    "Alicja",
                    "Pils",
                    "P.ala@gmail.com",
                    Type.first_line,
                    LocalDate.of(1985, JUNE,22)
            );

            Manager wojciech = new Manager(
                    "Wojciech",
                    "Kaczka",
                    "kaczor@gmail.com",
                    Type.top_level,
                    LocalDate.of(1991, JUNE,30)
            );

            Manager agata = new Manager(
                    "Agata",
                    "Rids",
                    "agataR@gmail.com",
                    Type.middle,
                    LocalDate.of(1999, MAY,26)
            );

            Manager agnieszka = new Manager(
                    "Agnieszka",
                    "Kozioł",
                    "koziołek@gmail.com",
                    Type.team,
                    LocalDate.of(2000, OCTOBER,2)
            );

            repository.saveAll(
                    List.of(alicja, agata, agnieszka, wojciech)
            );
        };
    }
}
