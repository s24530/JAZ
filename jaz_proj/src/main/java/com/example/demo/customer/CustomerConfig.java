package com.example.demo.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args ->{
           Customer patryk = new Customer(
                    "Patryk",
                    "Kołacz",
                    "patykk@gmail.com",
                    LocalDate.of(1995, JUNE,12)
            );

            Customer magda = new Customer(
                    "Magda",
                    "Bereda",
                    "madzia@gmail.com",
                    LocalDate.of(1993, DECEMBER,31)
            );

            Customer kamil = new Customer(
                    "Kamil",
                    "Slimak",
                    "KamilSlimak@gmail.com",
                    LocalDate.of(1999, MAY,24)
            );
            repository.saveAll(
                    List.of(patryk, magda, kamil)
            );
        };
    }
}
