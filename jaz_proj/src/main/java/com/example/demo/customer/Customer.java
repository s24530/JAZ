package com.example.demo.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dob; //date of birth
    @Transient
    private Integer age;

    public Customer() {
    }

    public Customer(Long id, String name, String surname, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.surname=surname;
        this.email = email;
        this.dob = dob;
    }

    public Customer(String name, String surname, String email, LocalDate dob) {
        this.name = name;
        this.surname=surname;
        this.email = email;
        this.dob = dob;
    }
    /*** Can use from lombock
     *
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
     */

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
