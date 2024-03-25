package com.example.authentication.service;

import com.example.authentication.entity.Employe;
import com.example.authentication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeService {
    @Autowired
    private RestTemplate restTemplate;

   /* public Carriere getCarriereById(Long carriereId) {
        String carriereServiceUrl = "http://carriere-service/careers/" + carriereId;
        ResponseEntity<Carriere> response = restTemplate.getForEntity(carriereServiceUrl, Carriere.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle error or return null
            return null;
        }
    }*/

    public void saveEmployee(Employe employee) {

       // User savedUser = restTemplate.postForObject("http://user-service/users", employee.getUser(), User.class);

    //    Career savedCareer = restTemplate.postForObject("http://career-service/careers", employee.getCareer(), Career.class);

    }
}
