package org.vaadin.marcus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.vaadin.marcus.data.Booking;
//import org.vaadin.marcus.data.BookingData;
//import org.vaadin.marcus.data.BookingStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.vaadin.marcus.data.Reclamation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ReclamationService {

    private final ReclamationRepository reclamationRepository;


    @Autowired
    public ReclamationService(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
     //   initDemoData();
    }





    //**************************************************************************************
    public Reclamation findReclamation(Long reclamationId , String matricule, String firstName, String lastName) {
        return reclamationRepository.findByReclamationIdAndMatriculeAndFirstNameAndLastNameIgnoreCase(reclamationId,matricule, firstName, lastName)
                .orElseThrow(() -> new IllegalArgumentException("Reclamation not found"));
    }


    public ReclamationDetails getReclamationDetails(Long reclamationId,String matricule, String firstName, String lastName) {
        var reclamation = findReclamation(reclamationId,matricule, firstName, lastName);
        return toReclamationDetails(reclamation);
    }

    public void changeReclamation(Long reclamationId, String firstName, String lastName, String newDate, String description, String matricule) {
        var reclamation = findReclamation(reclamationId,matricule, firstName, lastName);

        if (reclamation.getDate().isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("La réclamation ne peut pas être modifiée après 24 heures suivant la date de début.");
        }

        reclamation.setDate(LocalDate.parse(newDate));
        reclamation.setDescription(description);

        reclamationRepository.save(reclamation);
    }

    public void cancelReclamation(Long reclamationId, String matricule, String firstName, String lastName) {
        var reclamation = findReclamation(reclamationId, matricule, firstName, lastName);

        // Check if the reclamation is within 24 hours of the start date
        if (ChronoUnit.HOURS.between(reclamation.getDate().atStartOfDay(), LocalDate.now().atStartOfDay()) > 24) {
            throw new IllegalArgumentException("Reclamation cannot be cancelled if more than 24 hours after the start date.");
        }

        // Cancel the reclamation
        reclamation.setReclamationStatut("CANCELLED");

        // Save the updated reclamation
        reclamationRepository.save(reclamation);
    }

    public ResponseEntity<String> acceptReclamation(Long reclamationId) {
        Optional<Reclamation> reclamationOptional = reclamationRepository.findById(reclamationId);

        if (reclamationOptional.isPresent()) {
            Reclamation reclamation = reclamationOptional.get();

            reclamation.setReclamationStatut("CONFIRMED");

            reclamationRepository.save(reclamation);

            return ResponseEntity.ok("Reclamation accepted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<String> deleteReclamation(Long reclamationId) {
        Optional<Reclamation> reclamationOptional = reclamationRepository.findById(reclamationId);

        if (reclamationOptional.isPresent()) {
            Reclamation reclamation = reclamationOptional.get();


            reclamationRepository.deleteById(reclamation.getReclamationId());
            return ResponseEntity.ok("Reclamation deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    private ReclamationDetails toReclamationDetails(Reclamation reclamation) {
        return new ReclamationDetails(
                reclamation.getReclamationId(),
                reclamation.getMatricule(),
                reclamation.getFirstName(),
                reclamation.getLastName(),
                reclamation.getEmail(),
                reclamation.getTel(),
                reclamation.getEntreprise(),
                reclamation.getDate(),
                reclamation.getDescription(),
                reclamation.getReclamationStatut()
        );
    }

    public void addReclamation(String matricule,String firstName, String lastName, String email,String tel, String entreprise, String description
                               ) {
        LocalDate currentDateTime = LocalDate.now();

        Reclamation reclamation = new Reclamation();
        reclamation.setMatricule(matricule);
        reclamation.setFirstName(firstName);
        reclamation.setLastName(lastName);
        reclamation.setEmail(email);
        reclamation.setTel(tel);
        reclamation.setEntreprise(entreprise);
        reclamation.setDate(currentDateTime);
        reclamation.setDescription(description);

        reclamation.setReclamationStatut("IN PROGRESS");

        // Save the reclamation to the database
        reclamationRepository.save(reclamation);
        SendConfirmation(reclamation.getReclamationId());

        System.out.println("Reclamation added successfully");
    }

    @Autowired
    private EmailSenderService senderService;
    public ResponseEntity<String> SendConfirmation (@PathVariable Long id) {
        try {
            Optional<Reclamation> optionalUser = reclamationRepository.findById(id);


            if (optionalUser.isPresent()) {
                Reclamation Reclamation = optionalUser.get();

                String candidateEmail = Reclamation.getEmail();
                senderService.sendSimpleEmail(candidateEmail,
                        "Reclamation added successfully",
                        "Dear " + Reclamation.getFirstName() + " " + Reclamation.getLastName() + ",\n\n" +
                                "Welcome to our platform! We are excited to have you as a  member of our community.\n\n" +
                                "Your Reclamation has been successfully created and activated. this more details about your reclamation:\n" +
                                "Reclamation ID : "+ Reclamation.getReclamationId() +"\n" +
                                "Reclamation : "+ Reclamation.getDescription() +"\n" +
                                "Reclamation Date : "+ Reclamation.getDate() +"\n" +

                                "Thank you for contacting us. We will get back to you as soon as possible. You can cancel your reclamation within 24 hours from sending it.\n\n" +
                                "Best regards,\n" +
                                "The Platform Team");

                return ResponseEntity.ok("Email sent successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamation not found ");
            }
        } catch (Exception e) {
            // Log the exception or return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }

    }

    public ResponseEntity<String> SendConfirmation2 (@PathVariable Long id) {
        try {
            Optional<Reclamation> optionalUser = reclamationRepository.findById(id);


            if (optionalUser.isPresent()) {
                Reclamation Reclamation = optionalUser.get();

                String candidateEmail = Reclamation.getEmail();
                senderService.sendSimpleEmail(candidateEmail,
                        "Reclamation processed successfully",
                        "Dear " + Reclamation.getFirstName() + " " + Reclamation.getLastName() + ",\n\n" +
                                "Welcome to our platform! We are excited to have you as a  member of our community.\n\n" +
                                "Your Reclamation has been successfully processed and activated. this more details about your reclamation:\n" +
                                "Reclamation ID : "+ Reclamation.getReclamationId() +"\n" +
                                "Reclamation : "+ Reclamation.getDescription() +"\n" +
                                "Reclamation Date : "+ Reclamation.getDate() +"\n" +

                                "Thank you for your message. Your reclamation has been processed successfully. We appreciate your patience and understanding.\n\n" +
                                "Best regards,\n" +
                                "The Platform Team");

                return ResponseEntity.ok("Email sent successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamation not found ");
            }
        } catch (Exception e) {
            // Log the exception or return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }

    }
    public long getTotalReclamations() {
        return reclamationRepository.countAllReclamations();
    }

}
