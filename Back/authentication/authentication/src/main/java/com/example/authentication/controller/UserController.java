package com.example.authentication.controller;

import com.example.authentication.dao.*;
import com.example.authentication.entity.Employe;
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
    private EmployeDao EmployeDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDTO UserDTO;

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

    @PostMapping("/registerNewEmploye/{id}")
    public ResponseEntity<?> registerNewUserV2(@PathVariable String id, @RequestBody Employe updatedEmploye) {
        try {
            ResponseEntity<?> registeredEmploye = userService.registerNewUserV2(id,updatedEmploye);
            return ResponseEntity.ok(registeredEmploye);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/AddNewUser")
    public ResponseEntity<User> registerNewUser(@RequestBody RegistrationRequest request) {
        String password = request.getUser().getUserPassword();

        try {
            // Register the new user
            User newUser = userService.AddNewUser(request.getUser(),  request.getRoleName(), request.getEntrepriseName());

            String candidateEmail = newUser.getEmail();

            senderService.sendSimpleEmail(candidateEmail,
                    "Validation du Compte",
                    "Bonjour " + newUser.getUserFirstName() + " " + newUser.getUserLastName() + ",\n\n" +
                            "Bienvenue au sein de notre entreprise !\n\n" +
                            "C'est avec un immense plaisir que nous vous annonçons votre intégration à notre équipe.\n" +
                            "Nous sommes véritablement enchantés de vous accueillir parmi nous et nous espérons sincèrement que vous tirerez pleinement profit de votre expérience au sein de notre entreprise !\n" +
                            "Votre demande a été confirmée et nous avons hâte de partager cette balade avec vous.\n" +
                            "Votre participation est très appréciée, et nous pensons que votre présence contribuera positivement.\n\n" +
                            "Voici vos identifiants pour accéder à notre plateforme :\n\n" +
                            "Email : " + newUser.getEmail() + "\n" +
                            "Mot de Passe : " + password + "\n\n" +
                            "Rh Teams" + ",\n" +
                            request.getEntrepriseName());

            // Return the newly registered user with HTTP status 200 OK
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            // Log the exception or return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }







    @PutMapping("/updateUserRole/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable String id,@RequestBody RegistrationRequest request) {
        return userService.updateUserById(id, request.getUser(), request.getRoleName());
    }

    @PutMapping("/updateContrat/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Employe updatedContrat) {
        return userService.updateContratById(id, updatedContrat);

    }

        @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
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

    @GetMapping("/AllEmployes")
    public List<Employe> getAllEmployes() {
        return EmployeDao.findAll();
    }
    @GetMapping("/AllUsers/Admin")
    public List<User> getAllAdminUsers() {
        return userDao.findAll().stream()
                .filter(user -> user.getRole().stream()
                        .anyMatch(role -> "Gerant".equals(role.getRoleName())))
                .collect(Collectors.toList());
    }

    @GetMapping("/AllGRH/{Entreprise}")
    public List<UserDTO> getAllGRH(@PathVariable String Entreprise) {
        return userDao.findAll().stream()
                .filter(user -> user.getRole().stream()
                        .anyMatch(role -> "GRH".equals(role.getRoleName())))
                .filter(user -> user.getEntreprise().stream()
                        .anyMatch(entreprise -> Entreprise.equals(entreprise.getEntrepriseName())))
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setEmail(user.getEmail());
                    dto.setPrivate_email(user.getPrivate_email());
                    dto.setUserFirstName(user.getUserFirstName());
                    dto.setUserLastName(user.getUserLastName());
                    dto.setCin(user.getCin());
                    dto.setUserPassword(user.getUserPassword());
                    dto.setCivility(user.getCivility());
                    dto.setMatricule(user.getMatricule());
                    dto.setDateOfBirth(user.getDateOfBirth());
                    dto.setPlaceOfBirth(user.getPlaceOfBirth());
                    dto.setNationality(user.getNationality());
                    dto.setGender(user.getGender());
                    dto.setCinDate(user.getCinDate());
                    dto.setPhoneNumber(user.getPhoneNumber());
                    dto.setAddress(user.getAddress());
                    dto.setPays(user.getPays());
                    dto.setCodePostal(user.getCodePostal());
                    dto.setNiveauEtude(user.getNiveauEtude());
                    dto.setDateDernierDiplome(user.getDateDernierDiplome());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @GetMapping("/AllEmploye/{Entreprise}")
    public List<UserDTO> getAllEmploye(@PathVariable String Entreprise) {
        return userDao.findAll().stream()
                .filter(user -> user.getRole().stream()
                        .anyMatch(role -> "User".equals(role.getRoleName())))
                .filter(user -> user.getEntreprise().stream()
                        .anyMatch(entreprise -> Entreprise.equals(entreprise.getEntrepriseName())))
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setEmail(user.getEmail());
                    dto.setPrivate_email(user.getPrivate_email());
                    dto.setUserFirstName(user.getUserFirstName());
                    dto.setUserLastName(user.getUserLastName());
                    dto.setCin(user.getCin());
                    dto.setUserPassword(user.getUserPassword());
                    dto.setCivility(user.getCivility());
                    dto.setMatricule(user.getMatricule());
                    dto.setDateOfBirth(user.getDateOfBirth());
                    dto.setPlaceOfBirth(user.getPlaceOfBirth());
                    dto.setNationality(user.getNationality());
                    dto.setGender(user.getGender());
                    dto.setCinDate(user.getCinDate());
                    dto.setPhoneNumber(user.getPhoneNumber());
                    dto.setAddress(user.getAddress());
                    dto.setPays(user.getPays());
                    dto.setCodePostal(user.getCodePostal());
                    dto.setNiveauEtude(user.getNiveauEtude());
                    dto.setDateDernierDiplome(user.getDateDernierDiplome());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @GetMapping("/Employe/{id}")
    public ResponseEntity<User> getEmployeById(@PathVariable String id) {
        Optional<User> employeOptional = userDao.findById(id);
        Optional<Employe> employeOptional1 = EmployeDao.findById(id);

        if (employeOptional.isPresent()) {
            User User = employeOptional.get();
            Employe Employe = employeOptional1.get();

            return ResponseEntity.ok(User);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Adminstrative/{id}")
    public ResponseEntity<Employe> getAdminstrativeById(@PathVariable String id) {
        Optional<Employe> employeOptional = EmployeDao.findById(id);

        if (employeOptional.isPresent()) {
          Employe Employe = employeOptional.get();

            return ResponseEntity.ok(Employe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<String> ActiverUser(@PathVariable String id) {
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
    public ResponseEntity<String> deleteById(@PathVariable("id") String id, @PathVariable("entrepriseId") String entrepriseId) {
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
    @PutMapping("/archiveGerant/{id}")
    public ResponseEntity<String> archiveUserById(@PathVariable String id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("Archiver").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setEmail(null);


            // Set entreprise etat to -1
            user.getEntreprise().forEach(entreprise -> entreprise.setEtat(-1));

            userDao.save(user);
            return ResponseEntity.ok("User archived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}
    @PutMapping("/desarchiveGerant/{id}")
    public ResponseEntity<String> BackarchiveUserById(@PathVariable String id) {
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Role role = roleDao.findById("Gerant").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setEmail(user.getPrivate_email());

            user.getEntreprise().forEach(entreprise -> entreprise.setEtat(1));

            userDao.save(user);
            return ResponseEntity.ok("User desarchived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}


    @PutMapping("/archiveGRH/{id}")
    public ResponseEntity<String> archiveGRHById(@PathVariable String id) {
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Role role = roleDao.findById("Archiver").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setEmail(null);

            userDao.save(user);
            return ResponseEntity.ok("User archived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}
    @PutMapping("/desarchiveGRH/{id}")
    public ResponseEntity<String> DarchiveUserById(@PathVariable String id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("GRH").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setEmail(user.getPrivate_email());

            userDao.save(user);
            return ResponseEntity.ok("User desarchived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}
    @PutMapping("/archiveEmploye/{id}")
    public ResponseEntity<String> archiveEmployeById(@PathVariable String id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Role role = roleDao.findById("Archiver").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setEmail(null);

            userDao.save(user);
            return ResponseEntity.ok("User desarchived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}

    @PutMapping("/desarchiveEmploye/{id}")
    public ResponseEntity<String> DarchiveEmployeById(@PathVariable String id) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user roles to 'Archiver'
            Role role = roleDao.findById("User").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setEmail(user.getPrivate_email());




            userDao.save(user);
            return ResponseEntity.ok("User desarchived successfully");
        } else {
            return ResponseEntity.notFound().build();
        }}
    @Autowired
    private EmailSenderService senderService;


    @PostMapping("/SendConfirmation/{id}")
    public ResponseEntity<String> SendConfirmation (@PathVariable String id) {
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

    @GetMapping("/totalEnterprises")
    public long getTotalEnterprises() {
        return userService.getTotalEnterprises();
    }

    @GetMapping("/totalUsers")
    public long getTotalUsers() {
        return userService.getTotalUsers();
    }

    @GetMapping("/totalUsersWithRoleNewDemande")
    public long getTotalUsersWithRoleNewDemande() {
        return userService.getTotalUsersWithRoleNewDemande();
    }

}
