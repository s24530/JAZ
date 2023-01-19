package com.example.demo.manager;

import com.example.demo.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository
        extends JpaRepository<Manager, Long> {

    @Query("SELECT m FROM Manager m WHERE m.email = ?1")
    Optional<Manager> findManagerByEmail(String email);

    @Query("SELECT m FROM Manager m WHERE m.id = ?1")
    Optional<Manager> findManagerById(Long Id);
}

