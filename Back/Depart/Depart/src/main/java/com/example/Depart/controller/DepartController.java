package com.example.Depart.controller;


import com.example.Depart.entity.Depart;
import com.example.Depart.entity.DepartureRequest;
import com.example.Depart.repository.Depart_repo;
import com.example.Depart.service.Depart_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Depart.service.EmailSenderService;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DepartController {
    @Autowired
    private Depart_service Depart_service;
    @Autowired
    private Depart_repo Depart_repo;


    @PostMapping({"/DemandeDepart"})
    public  ResponseEntity<String> createNewCarrier(@RequestBody Depart Depart) {
        try {
            return Depart_service.create(Depart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping({"/GetDepart/{matricule}"})
    public Optional<Depart> GetCarrierByCin(@PathVariable String matricule) {
        return Depart_service.afficher(matricule);
    }

    @GetMapping({"/AllDepart"})
    public List<Depart> GetCarrierByCin() {
        return Depart_service.getAllDeparts();
    }

//    @PutMapping({"/UpdateCarrier/{cin}"})
//    public  ResponseEntity<String> UpdateCarrier(@PathVariable int cin, @RequestBody Depart updatedDepart) {
//        try {
//            return Depart_service.updateCarriereById(cin,updatedDepart);
//        } catch (Depart_service e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/acceptDeparture")
    public ResponseEntity<String> acceptDeparture(@RequestBody DepartureRequest request) {
        try {


            Optional<Depart> optionalDeparture = Depart_repo.findById(request.getDepartId());


            if (optionalDeparture.isEmpty()) {
                return ResponseEntity.badRequest().body("Departure with ID " + request.getDepartId() + " not found.");
            }

            Depart departure = optionalDeparture.get();
            departure.setSituation("Confirmé");

            Depart_service.acceptDeparture(departure);

            sendDepartureConfirmationEmail(request.getEmployeeName(), request.getEmployeeEmail(), departure);
            return ResponseEntity.ok("Demande de départ acceptée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'acceptation de la demande de départ : " + e.getMessage());
        }
    }
    @PostMapping("/annulerDeparture")
    public ResponseEntity<String> annulerDeparture(@RequestBody DepartureRequest request) {
        try {


            Optional<Depart> optionalDeparture = Depart_repo.findById(request.getDepartId());


            if (optionalDeparture.isEmpty()) {
                return ResponseEntity.badRequest().body("Departure with ID " + request.getDepartId() + " not found.");
            }

            Depart departure = optionalDeparture.get();
            departure.setSituation("Refuser");
            Depart_service.annulerDeparture(departure);
            sendDepartureAnnulationEmail(request.getEmployeeName(), request.getEmployeeEmail(), departure);

            return ResponseEntity.ok("Demande de départ annulée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'acceptation de la demande de départ : " + e.getMessage());
        }
    }
    @Autowired
    private EmailSenderService senderService;
    private void sendDepartureConfirmationEmail(String employeeName, String employeeEmail, Depart savedDeparture) {
        String departureDetails = "ID du départ : " + savedDeparture.getDepartId() +
                "\nID de l'employé : " + savedDeparture.getCin() +
                "\nDate de départ : " + savedDeparture.getDepartDate() +
                "\nType de départ : " + savedDeparture.getDepartType();

        senderService.sendSimpleEmail(employeeEmail,
                "Confirmation de Départ",
                "Cher/Chère " + employeeName + ",\n\n" +
                        "Nous avons le plaisir de vous informer que votre demande de départ a été acceptée avec succès. Voici les détails :\n\n" +
                        departureDetails + "\n\n" +
                        "Si vous avez d'autres questions ou des préoccupations, n'hésitez pas à nous contacter.\n\n" +
                        "Cordialement,\n" +
                        "Rh Equipe");
    }
    private void sendDepartureAnnulationEmail(String employeeName, String employeeEmail, Depart savedDeparture) {
        String departureDetails = "ID du départ : " + savedDeparture.getDepartId() +
                "\nID de l'employé : " + savedDeparture.getCin() +
                "\nDate de départ : " + savedDeparture.getDepartDate() +
                "\nType de départ : " + savedDeparture.getDepartType();

        senderService.sendSimpleEmail(employeeEmail,
                "Annulation de Départ",
                "Cher/Chère " + employeeName + ",\n\n" +
                        "Nous sommes désolés de vous informer que votre demande de départ a été annulée. \n " +
                        "Nous comprenons que cela puisse être décevant, mais nous restons à votre disposition pour toute question ou préoccupation que vous pourriez avoir. Voici les détails :\n\n" +
                        departureDetails + "\n\n" +
                        "Si vous avez d'autres questions ou des préoccupations, n'hésitez pas à nous contacter.\n\n" +
                        "Cordialement,\n" +
                        "Rh Equipe");
    }
    @DeleteMapping({"/DeleteDepart/{cin}"})
    public ResponseEntity<String> DelCarrierByCin(@PathVariable int cin) {
        return Depart_service.deleteDepart(cin);
    }
}
