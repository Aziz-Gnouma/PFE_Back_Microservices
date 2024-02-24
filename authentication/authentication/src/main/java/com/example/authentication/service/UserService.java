package com.example.authentication.service;

import com.example.authentication.dao.EntrepriseDao;
import com.example.authentication.dao.RoleDao;
import com.example.authentication.dao.UserDao;
import com.example.authentication.entity.Entreprise;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
@Autowired
private  EntrepriseDao EntrepriseDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Entreprise Entreprise = new Entreprise();
        Entreprise.setEntrepriseName("Medianet");
        Entreprise.setEntrepriseDescription("DSI");
        Entreprise.setAdresse_Entreprise("Montplaisir");
        Entreprise.setVille("Tunis");
        Entreprise.setCode_Postal("1000");
        Entreprise.setPays("Tunisia");
        Entreprise.setTel_Entreprise("22345678");
        Entreprise.setEmail("info@medianet.tn");
        Entreprise.setNumFiscale("1234567890");
        Entreprise.setDate_Creation_Entreprise(new Date(118, 4, 20)); // You need to use new Date(year, month, day) for setting the date
        Entreprise.setDomaine_Activite("IT");
        Entreprise.setNb_Employees(100);
        Entreprise.setSite_Web("www.medianet.tn");
        Entreprise.setEtat(1);        EntrepriseDao.save(Entreprise);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        Role ArchiverRole = new Role();
        ArchiverRole.setRoleName("Archiver");
        ArchiverRole.setRoleDescription("Default role for newly created record");
        roleDao.save(ArchiverRole);
        Role GerantRole = new Role();
        GerantRole.setRoleName("Gerant");
        GerantRole.setRoleDescription("'Gérant' can be likened to that of the entrepreneur within the enterprise");
        roleDao.save(GerantRole);

        User adminUser = new User();
        adminUser.setCin(14659847);
        adminUser.setEmail("aziz@aziz.com");
        adminUser.setUserPassword(getEncodedPassword("aziz@123"));
        adminUser.setUserFirstName("test");
        adminUser.setUserLastName("test2");
        adminUser.setCivility("Mr");
        adminUser.setMatricule(1263456);
        adminUser.setDateOfBirth(new Date(100, 4, 25));
        adminUser.setPlaceOfBirth("nabeul");
        adminUser.setNationality("Tunisia");
        adminUser.setGender("Male");
        adminUser.setCinDate(new Date(118, 4, 20));
        adminUser.setPhoneNumber(23679560);
        adminUser.setAddress("nabeul");
        adminUser.setLocality("Nabeul");
        Set<Role> GerantRoles = new HashSet<>();
        GerantRoles.add(GerantRole);
        adminUser.setRole(GerantRoles);
        Entreprise entreprise = EntrepriseDao.findById("Medianet").get();

        Set<Entreprise> userEntreprise = new HashSet<>();
        userEntreprise.add(entreprise);
        adminUser.setEntreprise(userEntreprise);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
    public User registerNewUser(User user, Entreprise entreprise) {
        if (userDao.existsByEmail(user.getEmail()) || userDao.existsByCin(user.getCin())) {
            throw new RuntimeException("User with the provided email or cin already exists");
        }

        // Check if entreprise with the provided ID, email, adresse, or num fiscale already exists
        if (EntrepriseDao.existsById(entreprise.getEntrepriseName()) ||
                EntrepriseDao.existsByEmail(entreprise.getEmail()) ||
                EntrepriseDao.existsByNumFiscale(entreprise.getNumFiscale())) {
            throw new RuntimeException("Entreprise already exists");
        }


        // Retrieve the role
        Role role = roleDao.findById("User").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

        // Associate the role with the user
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);

        // Encode the user password
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        // Create a Set containing the Entreprise object
        Set<Entreprise> entreprises = new HashSet<>();
        entreprises.add(entreprise);

        // Associate the Set<Entreprise> with the user
        user.setEntreprise(entreprises);

        // Save the user (and associated entreprise)
        return userDao.save(user);
    }


    public User AddNewUser(User user, String roleNames , String entrepriseName) {
        if (entrepriseName == null || entrepriseName.isEmpty()) {
            throw new IllegalArgumentException("Entreprise name cannot be null or empty");
        }
        Entreprise entreprise = EntrepriseDao.findById(entrepriseName).get();
        Set<Entreprise> userEntreprise = new HashSet<>();
        userEntreprise.add(entreprise);
        user.setEntreprise(userEntreprise);


        Role role = roleDao.findById(roleNames).get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }


    public ResponseEntity<String>  updateUserById(int id, User updatedUser,  String roles) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user information based on the fields you want to allow the user to update
            if (updatedUser.getUserFirstName() != null && !updatedUser.getUserFirstName().isEmpty()) {
            user.setUserFirstName(updatedUser.getUserFirstName());
        }
            if (updatedUser.getUserLastName() != null && !updatedUser.getUserLastName().isEmpty()) {
            user.setUserLastName(updatedUser.getUserLastName());
        }
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getCivility() != null) {
                user.setCivility(updatedUser.getCivility());
            }
            if (updatedUser.getMatricule() != null) {
                user.setMatricule(updatedUser.getMatricule());
            }
            if (updatedUser.getDateOfBirth() != null) {
                user.setDateOfBirth(updatedUser.getDateOfBirth());
            }
            if (updatedUser.getPlaceOfBirth() != null) {
                user.setPlaceOfBirth(updatedUser.getPlaceOfBirth());
            }
            if (updatedUser.getNationality() != null) {
                user.setNationality(updatedUser.getNationality());
            }
            if (updatedUser.getGender() != null) {
                user.setGender(updatedUser.getGender());
            }
            if (updatedUser.getCinDate() != null) {
                user.setCinDate(updatedUser.getCinDate());
            }
            if (updatedUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            if (updatedUser.getAddress() != null) {
                user.setAddress(updatedUser.getAddress());
            }
            if (updatedUser.getLocality() != null) {
                user.setLocality(updatedUser.getLocality());
            }
            // Check if the password is provided before updating
            if (updatedUser.getUserPassword() != null && !updatedUser.getUserPassword().isEmpty()) {
                user.setUserPassword(passwordEncoder.encode(updatedUser.getUserPassword())); // Encrypt the password
            }

            Role role = roleDao.findById(roles).get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);

        userDao.save(user);
            return ResponseEntity.ok("User information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<String> updateUser(int id, User updatedUser) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user information based on the fields you want to allow the user to update
            user.setUserFirstName(updatedUser.getUserFirstName());
            user.setUserLastName(updatedUser.getUserLastName());
            user.setEmail(updatedUser.getEmail());

            // Check if the password is provided before updating
            if (updatedUser.getUserPassword() != null && !updatedUser.getUserPassword().isEmpty()) {
                user.setUserPassword(passwordEncoder.encode(updatedUser.getUserPassword())); // Encrypt the password
            }


            userDao.save(user);
            return ResponseEntity.ok("User information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
