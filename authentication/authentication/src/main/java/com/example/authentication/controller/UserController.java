package com.example.authentication.controller;

import com.example.authentication.dao.RegistrationRequest;
import com.example.authentication.dao.RoleDao;
import com.example.authentication.dao.EntrepriseDao;
import com.example.authentication.dao.UserDao;
import com.example.authentication.entity.Entreprise;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.service.EmailSenderService;
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
        String pass = request.getUser().getUserPassword();

           // User newUser = userService.AddNewUser(request.getUser(), request.getRoleName(), request.getEntrepriseName());
            Optional<User> optionalUser = userDao.findById(request.getUser().getCin());

            // Check if the user is present
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                String candidateEmail = user.getEmail();
                senderService.sendSimpleEmail(candidateEmail,
                        "Validation du Compte",
                        "Bonjour " + user.getUserFirstName() + " " + user.getUserLastName() + ",\n\n" +
                                "Bienvenue au sein de notre entreprise !  \n\n\" "+
                                "C'est avec un immense plaisir que nous vous annonçons votre intégration à notre équipe. \n" +
                                "Nous sommes véritablement enchantés de vous accueillir parmi nous et nous espérons sincèrement que vous tirerez pleinement profit de votre expérience au sein de notre entreprise !\n" +
                                "Votre demande a été confirmée et nous avons hâte de partager cette balade avec vous. \n"+
                                "Votre participation est très appréciée, et nous pensons que votre présence contribuera positivement .\n\n" +
                                "Voici vos identifiants pour accéder à notre plateforme :\n\n" +
                                "Email : " + user.getEmail() + "\n" +
                                "Mot de Passe : " + pass+ "\n\n" +
                                "Rh Teams"  + ",\n" +
                                request.getEntrepriseName());

                // Assuming "dash" is the view name to be displayed after the acceptance process

            }
                return  userService.AddNewUser(request.getUser(), request.getRoleName(), request.getEntrepriseName());


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

    @PutMapping("/desarchiveGRH/{id}")
    public ResponseEntity<String> DarchiveUserById(@PathVariable int id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("GRH").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

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
    @Autowired
    private EmailSenderService senderService;


    @PostMapping("/SendConfirmation/{id}")
    public ResponseEntity<String> SendConfirmation (@PathVariable int id) {
        try {
            // Check if the user already exists
            Optional<User> optionalUser = userDao.findById(id);


            if (optionalUser.isPresent()) {
                User User = optionalUser.get();

                String candidateEmail = User.getEmail();
                senderService.sendSimpleEmail(candidateEmail,
                        "Confirmation and Activation of Your Account",
                        "Dear " + User.getUserFirstName() + " " + User.getUserLastName() + ",\n\n" +
                                "Welcome to our platform! We are excited to have you as a new member of our community.\n\n" +
                                "Your account has been successfully created and activated. Below are your login credentials:\n" +

                                "Thank you for joining us. We look forward to your active participation and contribution.\n\n" +
                                "Best regards,\n" +
                                "The Platform Team");

              return ResponseEntity.ok("Email sent successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found ");
            }
        } catch (Exception e) {
            // Log the exception or return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }

    }
}
