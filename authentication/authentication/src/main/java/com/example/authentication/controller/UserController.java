package com.example.authentication.controller;

import com.example.authentication.dao.RegistrationRequest;
import com.example.authentication.dao.RoleDao;
import com.example.authentication.dao.EntrepriseDao;
import com.example.authentication.dao.UserDao;
import com.example.authentication.entity.Entreprise;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private EntrepriseDao EntrepriseDao;


    @Autowired
    private RoleDao roleDao;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }


    // Add another method with a different name or parameters
    @PostMapping("/registerNewUser")
    public ResponseEntity<?> registerNewUserV2(@RequestBody RegistrationRequest request) {
        try {
            User registeredUser = userService.registerNewUser(request.getUser(), request.getEntreprise());
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/AddNewUser")
    public User registerNewUser(@RequestBody RegistrationRequest request) {
        return userService.AddNewUser(request.getUser(), request.getRoleName() ,request.getEntrepriseName());
    }


    @PutMapping("/updateUserRole/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable int id,@RequestBody RegistrationRequest request) {
        return userService.updateUserById(id, request.getUser(), request.getRoleName());
    }


    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);

    }
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return userService.createNewRole(role);
    }

    @GetMapping("/AllUsers")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
    @GetMapping("/AllUsers/Admin")
    public List<User> getAllAdminUsers() {
        return userDao.findAll().stream()
                .filter(user -> user.getRole().stream()
                        .anyMatch(role -> "Gerant".equals(role.getRoleName())))
                .collect(Collectors.toList());
    }
    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userDao.findById(id);

    }

    @GetMapping("/AllEntreprises")
    public List<Entreprise> getAllEntreprises() {
        return EntrepriseDao.findAll();
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @PostMapping("/ActiverUser/{id}")
    public ResponseEntity<String> ActiverUser(@PathVariable int id) {
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("Gerant").orElseThrow(() -> new EntityNotFoundException("Role 'Gerant' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);


            userDao.save(user);
            return ResponseEntity.ok("User Actived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}


    @DeleteMapping("/delete/{id}/entreprise/{entrepriseId}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id, @PathVariable("entrepriseId") String entrepriseId) {
        // Check if the user exists
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Remove the user's associated entreprises
            user.getEntreprise().clear();

            // Remove the user's associated roles
            user.getRole().clear();

            // Delete the user
            userDao.deleteById(id);
            EntrepriseDao.deleteById(entrepriseId);

            return ResponseEntity.ok("User and associated entreprise deleted successfully");
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }
    @PutMapping("/archiveUser/{id}")
    public ResponseEntity<String> archiveUserById(@PathVariable int id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("Archiver").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);

            // Set entreprise etat to -1
            user.getEntreprise().forEach(entreprise -> entreprise.setEtat(-1));

            userDao.save(user);
            return ResponseEntity.ok("User archived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}
    @PutMapping("/desarchiveUser/{id}")
    public ResponseEntity<String> BackarchiveUserById(@PathVariable int id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("Gerant").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);

            // Set entreprise etat to -1
            user.getEntreprise().forEach(entreprise -> entreprise.setEtat(1));

            userDao.save(user);
            return ResponseEntity.ok("User desarchived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}


}
