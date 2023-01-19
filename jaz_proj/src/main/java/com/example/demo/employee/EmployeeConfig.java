package com.example.demo.employee;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandRunner(EmployeeRepository repository) {
        return args ->{
            Employee maciej = new Employee(
                    "Maciej",
                    "Kowalski",
                    "Kowalski@gmail.com",
                    Contract.EMPLOYMENT,
                    LocalDate.of(2000, JANUARY,5)
            );

            Employee jakub = new Employee(
                    "Jakub",
                    "Jarosz",
                    "Jakub@gmail.com",
                    Contract.CONTRACT,
                    LocalDate.of(2004, JANUARY,5)
            );

            Employee milosz = new Employee(
                    "Milosz",
                    "Klacz",
                    "Milosz@gmail.com",
                    Contract.EMPLOYMENT,
                    LocalDate.of(1999, MARCH,5)
            );
            repository.saveAll(
                    List.of(jakub, maciej, milosz)
            );
        };
    }
}
