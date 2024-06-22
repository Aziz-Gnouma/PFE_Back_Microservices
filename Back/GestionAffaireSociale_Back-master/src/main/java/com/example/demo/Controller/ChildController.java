package com.example.demo.Controller;


import com.example.demo.DAO.ChildRepository;
import com.example.demo.Entity.Child;
import com.example.demo.Service.childservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/children")
public class ChildController {

    private final ChildRepository childRepository;
    private final childservice ChildService;


    @Autowired
    public ChildController(ChildRepository childRepository, childservice ChildService) {
        this.childRepository = childRepository;
        this.ChildService = ChildService;
    }


    @PostMapping("/add")
    public ResponseEntity<Child> addChild(@RequestBody Child child) {
        Child newChild = ChildService.ajouterChild(child);
        return new ResponseEntity<>(newChild, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Child>> getAllChildren() {
        List<Child> children = childRepository.findAll(); // Assuming ChildRepository has a findAll() method
        return new ResponseEntity<>(children, HttpStatus.OK);
    }
}
