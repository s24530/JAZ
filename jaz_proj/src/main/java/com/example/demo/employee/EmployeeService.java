package com.example.demo.employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(employeeOptional.isPresent()){
            throw new IllegalStateException("Employee with email " + employee.getEmail() + " already exists");
        }
        if(employee.getAge()<18){
            throw new IllegalStateException("Too young to register");
        }
        if(employee.getContractType()==null) employee.setContractType(Contract.CONTRACT);

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists){
            throw new IllegalStateException("Employee with id " + employeeId + " does not exist");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Long employeeId, String name, String surname, String email, Contract contract){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new IllegalStateException("Employee with id " + employeeId + " does not exist"));

        if(name !=null && name.length() > 0 && !Objects.equals(employee.getName(), name)){
            employee.setName(name);
        }

        if(surname !=null && surname.length() > 0 && !Objects.equals(employee.getSurname(), surname)){
            employee.setSurname(surname);
        }

        if(email != null && email.length() > 0 && !Objects.equals(employee.getEmail(), email)){
            Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
            if(employeeOptional.isPresent()){
                throw new IllegalStateException("Employee with id " + email + " already exists");
            }
            employee.setEmail(email);
        }

    }
}
