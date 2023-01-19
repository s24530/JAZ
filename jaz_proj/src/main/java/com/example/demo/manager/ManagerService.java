package com.example.demo.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManagerService {

    public final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository){
        this.managerRepository = managerRepository;
    }


    public List<Manager> getManagers() {
        return  managerRepository.findAll();
    }
    // test
    public Optional<Manager> getManager(Long managerId){
        return managerRepository.findManagerById(managerId);
    }

    public void addNewManager(Manager manager) {
        Optional<Manager> managerOptional = managerRepository.findManagerByEmail(manager.getEmail());
        if(managerOptional.isPresent()){
            throw new IllegalStateException("That manager already exists");
        }

        if(manager.getManagerType()==null) manager.setManagerType(Type.first_line);
        managerRepository.save(manager);
    }

    public void deleteManager(Long managerId) {
        boolean exists = managerRepository.existsById(managerId);
        if(!exists){
            throw new IllegalStateException("Manager with id " + managerId + " does not exist");
        }
        managerRepository.deleteById(managerId);
    }

    @Transactional
    public void updateManager(Long managerId, String name, String surname, String email, Type rank){
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(()-> new IllegalStateException("Manager with id " + managerId + " does not exist"));

        if(name !=null && name.length() > 0 && !Objects.equals(manager.getName(), name)){
            manager.setName(name);
        }

        if(surname !=null && surname.length() > 0 && !Objects.equals(manager.getSurname(), surname)){
            manager.setSurname(surname);
        }

        if(email != null && email.length() > 0 && !Objects.equals(manager.getEmail(), email)){
            Optional<Manager> managerOptional = managerRepository.findManagerByEmail(email);
            if(managerOptional.isPresent()){
                throw new IllegalStateException("That manager already exists");
            }
            manager.setEmail(email);
        }

    }
}
