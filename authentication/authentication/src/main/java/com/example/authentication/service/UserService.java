package com.example.authentication.service;

import com.example.authentication.dao.EmployeDao;
import com.example.authentication.dao.EntrepriseDao;
import com.example.authentication.dao.RoleDao;
import com.example.authentication.dao.UserDao;
import com.example.authentication.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    private EmployeDao EmployeDao ;
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

        Role RHRole = new Role();
        RHRole.setRoleName("GRH");
        RHRole.setRoleDescription("Default role for newly created record");
        roleDao.save(RHRole);

        Role SGRHRole = new Role();
        SGRHRole.setRoleName("SGRH");
        SGRHRole.setRoleDescription("Default role for newly created record");
        roleDao.save(SGRHRole);


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
        adminUser.setMatricule("Med_3456");
        adminUser.setDateOfBirth(new Date(100, 4, 25));
        adminUser.setPlaceOfBirth("nabeul");
        adminUser.setNationality("Tunisia");
        adminUser.setGender("Male");
        adminUser.setCinDate(new Date(118, 4, 20));
        adminUser.setPhoneNumber(23679560);
        adminUser.setAddress("nabeul");
        adminUser.setPays("Nabeul");
        Set<Role> AdminRole = new HashSet<>();
        AdminRole.add(adminRole);
        adminUser.setRole(AdminRole);
        Entreprise entreprise = EntrepriseDao.findById("Medianet").get();

        Set<Entreprise> userEntreprise = new HashSet<>();
        userEntreprise.add(entreprise);
        adminUser.setEntreprise(userEntreprise);
        userDao.save(adminUser);
     //   Employe ok = adminUser ;
       // EmployeDao.save(ok);


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
        if ( userDao.existsByMatricule(user.getMatricule())) {
            throw new RuntimeException("User already exists");
        }

        // Check if entreprise with the provided ID, email, adresse, or num fiscale already exists
        if (EntrepriseDao.existsById(entreprise.getEntrepriseName()) ||
                EntrepriseDao.existsByEmail(entreprise.getEmail()) ||
                EntrepriseDao.existsByNumFiscale(entreprise.getNumFiscale())) {
            throw new RuntimeException("Entreprise already exists");
        }


        Role role = roleDao.findById("User").orElseThrow(() -> new EntityNotFoundException("Role 'User' not found"));

        // Associate the role with the user
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);

        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        Set<Entreprise> entreprises = new HashSet<>();
        entreprises.add(entreprise);

        user.setEntreprise(entreprises);
        user.setPrivate_email(user.getEmail());

        return userDao.save(user);
    }


    public ResponseEntity<?> registerNewUserV2( String matricule,  Employe updatedEmploye) {
        try {
            Optional<Employe> optionalEmploye = EmployeDao.findById(matricule);
            Optional<User> userOptional = userDao.findById(matricule);

            if (optionalEmploye.isPresent()) {
                Employe employe = optionalEmploye.get();
                User user = userOptional.get();

                if (updatedEmploye.getUserFirstName() != null && !updatedEmploye.getUserFirstName().isEmpty()) {
                    employe.setUserFirstName(updatedEmploye.getUserFirstName());
                    user.setUserFirstName(updatedEmploye.getUserFirstName());
                }

                if (updatedEmploye.getUserLastName() != null && !updatedEmploye.getUserLastName().isEmpty()) {
                    employe.setUserLastName(updatedEmploye.getUserLastName());
                    user.setUserLastName(updatedEmploye.getUserLastName());
                }

                if (updatedEmploye.getPhoneNumber() != null) {
                    employe.setPhoneNumber(updatedEmploye.getPhoneNumber());
                    user.setPhoneNumber(updatedEmploye.getPhoneNumber());
                }

                if (updatedEmploye.getAddress() != null && !updatedEmploye.getAddress().isEmpty()) {
                    employe.setAddress(updatedEmploye.getAddress());
                    user.setAddress(updatedEmploye.getAddress());

                }

                if (updatedEmploye.getDateOfBirth() != null) {
                    employe.setDateOfBirth(updatedEmploye.getDateOfBirth());
                    user.setDateOfBirth(updatedEmploye.getDateOfBirth());
                }

                if (updatedEmploye.getPlaceOfBirth() != null && !updatedEmploye.getPlaceOfBirth().isEmpty()) {
                    employe.setPlaceOfBirth(updatedEmploye.getPlaceOfBirth());
                    user.setPlaceOfBirth(updatedEmploye.getPlaceOfBirth());
                }

                if (updatedEmploye.getGender() != null && !updatedEmploye.getGender().isEmpty()) {
                    employe.setGender(updatedEmploye.getGender());
                    user.setGender(updatedEmploye.getGender());
                }

                if (updatedEmploye.getCivility() != null && !updatedEmploye.getCivility().isEmpty()) {
                    employe.setCivility(updatedEmploye.getCivility());
                    user.setCivility(updatedEmploye.getCivility());
                }

                if (updatedEmploye.getCin() != 0) {
                    employe.setCin(updatedEmploye.getCin());
                    user.setCin(updatedEmploye.getCin());
                }

                if (updatedEmploye.getCinDate() != null) {
                    employe.setCinDate(updatedEmploye.getCinDate());
                    user.setCinDate(updatedEmploye.getCinDate());
                }

                if (updatedEmploye.getNationality() != null && !updatedEmploye.getNationality().isEmpty()) {
                    employe.setNationality(updatedEmploye.getNationality());
                    user.setNationality(updatedEmploye.getNationality());
                }

                if (updatedEmploye.getAddress()!= null && !updatedEmploye.getAddress().isEmpty()) {
                    employe.setAddress(updatedEmploye.getAddress());
                    user.setAddress(updatedEmploye.getAddress());
                }

                if (updatedEmploye.getCodePostal() != null && !updatedEmploye.getCodePostal().isEmpty()) {
                    employe.setCodePostal(updatedEmploye.getCodePostal());
                    user.setCodePostal(updatedEmploye.getCodePostal());
                }

                if (updatedEmploye.getPays() != null && !updatedEmploye.getPays().isEmpty()) {
                    employe.setPays(updatedEmploye.getPays());
                    user.setPays(updatedEmploye.getPays());
                }

                if (updatedEmploye.getEmail() != null && !updatedEmploye.getEmail().isEmpty()) {
                    employe.setEmail(updatedEmploye.getEmail());
                    user.setEmail(updatedEmploye.getEmail());
                    user.setPrivate_email(updatedEmploye.getEmail());

                }

                if (updatedEmploye.getNiveauEtude() != null && !updatedEmploye.getNiveauEtude().isEmpty()) {
                    employe.setNiveauEtude(updatedEmploye.getNiveauEtude());
                    user.setNiveauEtude(updatedEmploye.getNiveauEtude());
                }

                if (updatedEmploye.getDateDernierDiplome() != null) {
                    employe.setDateDernierDiplome(updatedEmploye.getDateDernierDiplome());
                    user.setDateDernierDiplome(updatedEmploye.getDateDernierDiplome());
                }

                if (updatedEmploye.getCodePostal() != null && !updatedEmploye.getCodePostal().isEmpty()) {
                    employe.setCodePostal(updatedEmploye.getCodePostal());
                    user.setCodePostal(updatedEmploye.getCodePostal());
                }

                if (updatedEmploye.getTypeContrat() != null && !updatedEmploye.getTypeContrat().isEmpty()) {
                    employe.setTypeContrat(updatedEmploye.getTypeContrat());
                }

                if (updatedEmploye.getTypeEmployeur() != null && !updatedEmploye.getTypeEmployeur().isEmpty()) {
                    employe.setTypeEmployeur(updatedEmploye.getTypeEmployeur());
                }

                if (updatedEmploye.getDateEntree() != null) {
                    employe.setDateEntree(updatedEmploye.getDateEntree());
                }

                if (updatedEmploye.getDateRecrutement() != null) {
                    employe.setDateRecrutement(updatedEmploye.getDateRecrutement());
                }

                if (updatedEmploye.getDateFinEssai() != null) {
                    employe.setDateFinEssai(updatedEmploye.getDateFinEssai());
                }

                if (updatedEmploye.getDateTitularisation() != null) {
                    employe.setDateTitularisation(updatedEmploye.getDateTitularisation());
                }

                if (updatedEmploye.isChefFamille()) {
                    employe.setChefFamille(updatedEmploye.isChefFamille());
                }

                if (updatedEmploye.isSalaireUnique()) {
                    employe.setSalaireUnique(updatedEmploye.isSalaireUnique());
                }

                if (updatedEmploye.isAllocationFamille()) {
                    employe.setAllocationFamille(updatedEmploye.isAllocationFamille());
                }

                if (updatedEmploye.getNumeroSecuriteSociale() != null && !updatedEmploye.getNumeroSecuriteSociale().isEmpty()) {
                    employe.setNumeroSecuriteSociale(updatedEmploye.getNumeroSecuriteSociale());
                }

                if (updatedEmploye.getDateAffiliation() != null) {
                    employe.setDateAffiliation(updatedEmploye.getDateAffiliation());
                }

                if (updatedEmploye.isExonereeSecuriteSociale()) {
                    employe.setExonereeSecuriteSociale(updatedEmploye.isExonereeSecuriteSociale());
                }

                if (updatedEmploye.getDateDebutExonereeSecuriteSociale() != null) {
                    employe.setDateDebutExonereeSecuriteSociale(updatedEmploye.getDateDebutExonereeSecuriteSociale());
                }

                if (updatedEmploye.getDateFinExonereeSecuriteSociale() != null) {
                    employe.setDateFinExonereeSecuriteSociale(updatedEmploye.getDateFinExonereeSecuriteSociale());
                }

                if (updatedEmploye.isAffiliationAssuranceGroupe()) {
                    employe.setAffiliationAssuranceGroupe(updatedEmploye.isAffiliationAssuranceGroupe());
                }

                if (updatedEmploye.getNomAssurance() != null && !updatedEmploye.getNomAssurance().isEmpty()) {
                    employe.setNomAssurance(updatedEmploye.getNomAssurance());
                }

                if (updatedEmploye.getNumeroAffiliationAssurance() != null && !updatedEmploye.getNumeroAffiliationAssurance().isEmpty()) {
                    employe.setNumeroAffiliationAssurance(updatedEmploye.getNumeroAffiliationAssurance());
                }
                if (updatedEmploye.getDateAffiliationAssurance() != null) {
                    employe.setDateAffiliationAssurance(updatedEmploye.getDateAffiliationAssurance());
                }

                if (updatedEmploye.isAffiliationMutuelle()) {
                    employe.setAffiliationMutuelle(updatedEmploye.isAffiliationMutuelle());
                }

                if (updatedEmploye.getNomMutuelle() != null && !updatedEmploye.getNomMutuelle().isEmpty()) {
                    employe.setNomMutuelle(updatedEmploye.getNomMutuelle());
                }

                if (updatedEmploye.getNumeroAffiliationMutuelle() != null && !updatedEmploye.getNumeroAffiliationMutuelle().isEmpty()) {
                    employe.setNumeroAffiliationMutuelle(updatedEmploye.getNumeroAffiliationMutuelle());
                }

                if (updatedEmploye.getDateAffiliationMutuelle() != null) {
                    employe.setDateAffiliationMutuelle(updatedEmploye.getDateAffiliationMutuelle());
                }

                if (updatedEmploye.getCategorie() != null && !updatedEmploye.getCategorie().isEmpty()) {
                    employe.setCategorie(updatedEmploye.getCategorie());
                }

                if (updatedEmploye.getGrade() != null && !updatedEmploye.getGrade().isEmpty()) {
                    employe.setGrade(updatedEmploye.getGrade());
                }

                if (updatedEmploye.getClasse() != null && !updatedEmploye.getClasse().isEmpty()) {
                    employe.setClasse(updatedEmploye.getClasse());
                }

                if (updatedEmploye.getEchelon() != null && !updatedEmploye.getEchelon().isEmpty()) {
                    employe.setEchelon(updatedEmploye.getEchelon());
                }

                if (updatedEmploye.getDateSituation() != null) {
                    employe.setDateSituation(updatedEmploye.getDateSituation());
                }

                if (updatedEmploye.getFonction() != null && !updatedEmploye.getFonction().isEmpty()) {
                    employe.setFonction(updatedEmploye.getFonction());
                }

                if (updatedEmploye.getDateFonction() != null) {
                    employe.setDateFonction(updatedEmploye.getDateFonction());
                }

                if (updatedEmploye.getStructureAttache() != null && !updatedEmploye.getStructureAttache().isEmpty()) {
                    employe.setStructureAttache(updatedEmploye.getStructureAttache());
                }

                if (updatedEmploye.getDateAffectation() != null && !updatedEmploye.getDateAffectation().isEmpty()) {
                    employe.setDateAffectation(updatedEmploye.getDateAffectation());
                }

                if (updatedEmploye.getMotifDepart() != null && !updatedEmploye.getMotifDepart().isEmpty()) {
                    employe.setMotifDepart(updatedEmploye.getMotifDepart());
                }

                if (updatedEmploye.getDateDepart() != null && !updatedEmploye.getDateDepart().isEmpty()) {
                    employe.setDateDepart(updatedEmploye.getDateDepart());
                }

                if (updatedEmploye.getSituation() != 0) {
                    employe.setSituation(updatedEmploye.getSituation());
                }

                if (updatedEmploye.getGrilleSalaire() != null && !updatedEmploye.getGrilleSalaire().isEmpty()) {
                    employe.setGrilleSalaire(updatedEmploye.getGrilleSalaire());
                }

                if (updatedEmploye.getSalaireDeBase() != 0.0) {
                    employe.setSalaireDeBase(updatedEmploye.getSalaireDeBase());
                }

                if (updatedEmploye.getModeDePaiement() != null && !updatedEmploye.getModeDePaiement().isEmpty()) {
                    employe.setModeDePaiement(updatedEmploye.getModeDePaiement());
                }

                if (updatedEmploye.getNumeroCompte() != null && !updatedEmploye.getNumeroCompte().isEmpty()) {
                    employe.setNumeroCompte(updatedEmploye.getNumeroCompte());
                }

                if (updatedEmploye.getNomBanque() != null && !updatedEmploye.getNomBanque().isEmpty()) {
                    employe.setNomBanque(updatedEmploye.getNomBanque());
                }

                if (updatedEmploye.getNomAgence() != null && !updatedEmploye.getNomAgence().isEmpty()) {
                    employe.setNomAgence(updatedEmploye.getNomAgence());
                }

                if (updatedEmploye.getMontantAssurance() != 0.0) {
                    employe.setMontantAssurance(updatedEmploye.getMontantAssurance());
                }

                if (updatedEmploye.getMontantMutuelle() != 0.0) {
                    employe.setMontantMutuelle(updatedEmploye.getMontantMutuelle());
                }


                // Similarly, update other attributes

                EmployeDao.save(employe);
                return ResponseEntity.ok("Employe information updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    public User AddNewUser(User user , String roleNames, String entrepriseName) {
        if (entrepriseName == null || entrepriseName.isEmpty()) {
            throw new IllegalArgumentException("Entreprise name cannot be null or empty");
        }
        try {
            if (userDao.existsByMatricule(user.getMatricule())) {
                throw new RuntimeException("User already exists");
            }

            Entreprise entreprise = EntrepriseDao.findById(entrepriseName).orElseThrow(() -> new RuntimeException("Entreprise not found"));
            Set<Entreprise> userEntreprise = new HashSet<>();
            userEntreprise.add(entreprise);
            user.setEntreprise(userEntreprise);


            Role role = roleDao.findById(roleNames).orElseThrow(() -> new RuntimeException("Role not found"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setPrivate_email(user.getEmail());
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));

            Employe employe = new Employe();
            employe.setEntrepriseName(entrepriseName);
            employe.setMatricule(user.getMatricule());
            employe.setUserFirstName(user.getUserFirstName());
            employe.setUserLastName(user.getUserLastName());
            employe.setCin(user.getCin()); // Assuming cin in Employe is a string
            employe.setCinDate(user.getCinDate());
            employe.setEmail(user.getEmail());
            employe.setPays(user.getPays());
            employe.setDateOfBirth(user.getDateOfBirth());
            employe.setPlaceOfBirth(user.getPlaceOfBirth());
            employe.setNationality(user.getNationality());
            employe.setGender(user.getGender());
            employe.setAddress(user.getAddress());
            employe.setPhoneNumber(user.getPhoneNumber());
            employe.setCivility(user.getCivility());

//            Set<administrative> Administratives = new HashSet<>();
//            Administratives.add(Administrative);
//
//            employe.setadministrative(Administratives);

//            employe.setTypeContrat(Administrative.getTypeContrat());
//            employe.setTypeEmployeur(Administrative.getTypeEmployeur());
//            employe.setDateEntree(Administrative.getDateEntree());
//            employe.setDateRecrutement(Administrative.getDateRecrutement());
//            employe.setDateFinEssai(Administrative.getDateFinEssai());
//            employe.setDateTitularisation(Administrative.getDateTitularisation());



            // Assuming Employee has a role and entreprise attribute


            EmployeDao.save(employe);
            userDao.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace(); // Or log the error
            throw new RuntimeException("An error occurred while adding the user");
        }
    }



    public ResponseEntity<String>  updateUserById(String matricule, User updatedUser,  String roles) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(matricule);
        Optional<Employe> employeeOptional = EmployeDao.findById(matricule);


        if (userOptional.isPresent()&& employeeOptional.isPresent()) {
            User user = userOptional.get();
            Employe employee = employeeOptional.get();

            if (updatedUser.getUserFirstName() != null && !updatedUser.getUserFirstName().isEmpty()) {
            user.setUserFirstName(updatedUser.getUserFirstName());
                employee.setUserFirstName(updatedUser.getUserFirstName());
            }
            if (updatedUser.getUserLastName() != null && !updatedUser.getUserLastName().isEmpty()) {
            user.setUserLastName(updatedUser.getUserLastName());
                employee.setUserLastName(updatedUser.getUserLastName());
        }
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
                user.setEmail(updatedUser.getEmail());
                user.setPrivate_email(updatedUser.getEmail());
                employee.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getCivility() != null) {
                user.setCivility(updatedUser.getCivility());
                employee.setCivility(updatedUser.getCivility());

            }
            if (updatedUser.getMatricule() != null) {
                user.setMatricule(updatedUser.getMatricule());
            }
            if (updatedUser.getDateOfBirth() != null) {
                user.setDateOfBirth(updatedUser.getDateOfBirth());
                employee.setDateOfBirth(updatedUser.getDateOfBirth());
            }
            if (updatedUser.getPlaceOfBirth() != null) {
                user.setPlaceOfBirth(updatedUser.getPlaceOfBirth());
                employee.setPlaceOfBirth(updatedUser.getPlaceOfBirth());
            }
            if (updatedUser.getNationality() != null) {
                user.setNationality(updatedUser.getNationality());
                employee.setNationality(updatedUser.getNationality());
            }
            if (updatedUser.getGender() != null) {
                user.setGender(updatedUser.getGender());
                employee.setGender(updatedUser.getGender());
            }
            if (updatedUser.getCinDate() != null) {
                user.setCinDate(updatedUser.getCinDate());
                employee.setCinDate(updatedUser.getCinDate());
            }
            if (updatedUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
                employee.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            if (updatedUser.getAddress() != null) {
                user.setAddress(updatedUser.getAddress());
                employee.setAddress(updatedUser.getAddress());

            }
            if (updatedUser.getPays() != null) {
                user.setPays(updatedUser.getPays());
                employee.setPays(updatedUser.getPays());
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

        //Employe

            return ResponseEntity.ok("User information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<String> updateUser(String matricule, User updatedUser) {
        // Fetch the existing user by ID
        Optional<User> userOptional = userDao.findById(matricule);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update user information based on the fields you want to allow the user to update
            user.setUserFirstName(updatedUser.getUserFirstName());
            user.setUserLastName(updatedUser.getUserLastName());
            user.setEmail(updatedUser.getEmail());

            if (updatedUser.getUserPassword() != null && !updatedUser.getUserPassword().isEmpty()) {
                user.setUserPassword(passwordEncoder.encode(updatedUser.getUserPassword()));
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
