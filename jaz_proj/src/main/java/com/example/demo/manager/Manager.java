package com.example.demo.manager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
/***
 * getters and setters from lombock
 */
@Entity
@Table
public class Manager {
    @Id
    @SequenceGenerator(
            name = "manager_sequence",
            sequenceName = "manager_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "manager_sequence"
    )
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Type managerType;
    private LocalDate dob; //date of birth
    @Transient
    private Integer age;

    public Manager() {
    }

    public Manager(Long id, String name, String surname, String email, Type managerType, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.managerType = managerType;
        this.dob = dob;
    }

    public Manager(String name, String surname, String email, Type managerType, LocalDate dob) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.managerType = managerType;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", managerType=" + managerType +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
