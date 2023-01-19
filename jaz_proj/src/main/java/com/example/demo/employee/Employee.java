package com.example.demo.employee;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@Entity
@Table
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Contract contractType;
    private LocalDate dob; //date of birth
    @Transient
    private Integer age;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, String email, Contract contractType, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contractType = contractType;
        this.dob = dob;
    }

    public Employee(String name, String surname, String email, Contract contractType, LocalDate dob) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contractType = contractType;
        this.dob = dob;
    }

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", contractType=" + contractType +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
