package com.example.demo.Service;

import com.example.demo.DAO.ChildRepository;
import com.example.demo.Entity.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class childservice {

    private final ChildRepository childRepository;

    @Autowired
    public childservice(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child ajouterChild(Child child) {
        return childRepository.save(child);
    }

    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    public Child getChildById(Long id) {
        return childRepository.findById(id).orElse(null);
    }

    public void deleteChildById(Long id) {
        childRepository.deleteById(id);
    }
}