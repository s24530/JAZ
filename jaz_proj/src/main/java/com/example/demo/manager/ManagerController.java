package com.example.demo.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService){
        this.managerService=managerService;
    }

    @GetMapping
    public List<Manager> getManagers(){
        return managerService.getManagers();
    }

    @GetMapping(path = "/seeManager/{managerId}")
    public Optional<Manager> getManagerById(@PathVariable("managerId") Long managerId){
        return managerService.getManager(managerId);
    }

    @PostMapping
    public void registerNewManager(@RequestBody Manager manager){
        managerService.addNewManager(manager);
    }

    @DeleteMapping(path = "{managerId}")
    public void deleteManager(@PathVariable("managerId") Long managerId){
        managerService.deleteManager(managerId);
    }

    @PutMapping(path = "{managerId}")
    public void updateManager (@PathVariable("managerId") Long managerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) Type rank){
        managerService.updateManager(managerId, name, surname, email, rank);
    }

}
