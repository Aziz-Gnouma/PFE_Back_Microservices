package com.Salaire.Salaire.Services;

import com.Salaire.Salaire.Repository.salaireRepo;
import com.Salaire.Salaire.entity.FicheDePaie;
import com.Salaire.Salaire.entity.Financieres;
import com.Salaire.Salaire.entity.IRPPRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class SalaireService {
    @Autowired
    private salaireRepo salaireRepo;

    public Map<String, Double> CalculerIRPP(String natureRevenuImposable, int parentsEnCharge, double autresDeductions,
                                            double montantMensuel, boolean chefDeFamille, int enfants, int enfantsSansBourse, int enfantsInfirmes) {

        double chef = 0;


        double Salaire_imposable_annuel = montantMensuel * 12;

        double fraisProfessionnels = 0;
        if (natureRevenuImposable.equals("salarie")) {
            fraisProfessionnels = Salaire_imposable_annuel * 0.10;
            if (fraisProfessionnels > 2000) {
                fraisProfessionnels = 2000;
            }
        } else {
            fraisProfessionnels = Salaire_imposable_annuel * 0.25;
        }

        if (chefDeFamille) {
            chef = 300;
            chef += parentsEnCharge * 400;

            if (enfantsSansBourse >= 4) {
                chef += enfantsSansBourse * 1000;
            } else {
                chef += enfantsSansBourse * 1000;
                chef += Math.min(enfants, 4) * 100;

            }

            chef += enfantsInfirmes * 2000;
        }

        double totalDeductions = chef + autresDeductions + fraisProfessionnels;
        double assietteImposable = Salaire_imposable_annuel - totalDeductions;

        double irpp = 0.0;

        if (assietteImposable <= 5000) {
            irpp += 0;
        } else if (assietteImposable <= 20000) {
            irpp += (assietteImposable - 5000) * 0.26;
        } else if (assietteImposable <= 30000) {
            irpp += 15000 * 0.26;
            irpp += (assietteImposable - 20000) * 0.28;
        } else if (assietteImposable <= 50000) {
            irpp += 15000 * 0.26;
            irpp += 10000 * 0.28;
            irpp += (assietteImposable - 30000) * 0.32;
        } else {
            irpp += 15000 * 0.26;
            irpp += 10000 * 0.28;
            irpp += 20000 * 0.32;
            irpp += (assietteImposable - 50000) * 0.35;
        }


        Map<String, Double> results = new HashMap<>();
        results.put("irppAnnuelle", irpp);
        results.put("totalDeductions", totalDeductions);
        results.put("assietteImposable", assietteImposable);
        results.put("CssAnnuelle", (assietteImposable * 0.5) / 100);


        return results;
    }


    public String CalculSalaire(String matricule, String natureRevenuImposable, int parentsEnCharge, double autresDeductions,
                                     double montantMensuel, boolean chefDeFamille, int enfants, int enfantsSansBourse,
                                     int enfantsInfirmes, String NomEntreprise , String AdresseEntreprise,String AffiliationCss, String CinEmploye, String NomEmploye, String Adresse,
                                     String NCss, String SituationFamiliale, String Fonction, boolean SecteurPrive) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based

        FicheDePaie existingFicheDePaie = salaireRepo.findByMatriculeAndDateAjouterYearAndDateAjouterMonth(matricule, currentMonth, currentYear);
        if (existingFicheDePaie != null) {
            return "Tu as déjà payé cet employé ce mois-ci. Merci !";
        }


        double cotisation = 0;
        if (SecteurPrive == true) {
            cotisation = (montantMensuel * 9.18) / 100;
        } else {
            cotisation = montantMensuel * 12.95 / 100;
        }


        double salaireImposable = montantMensuel - cotisation;

        Map<String, Double> irppDetails = CalculerIRPP(natureRevenuImposable, parentsEnCharge, autresDeductions,
                montantMensuel, chefDeFamille, enfants, enfantsSansBourse, enfantsInfirmes);

        double irppAnnuelle = irppDetails.get("irppAnnuelle")/12;
        double cssAnnuelle = irppDetails.get("CssAnnuelle")/12;

        double salaireNet = salaireImposable - irppAnnuelle - cssAnnuelle;
        Date currentDate = new Date();
        DecimalFormat df = new DecimalFormat("#.###");
        FicheDePaie ficheDePaie = new FicheDePaie();
        ficheDePaie.setMontantMensuel(montantMensuel);
        ficheDePaie.setCotisation(Math.round(cotisation * 1000.0) / 1000.0);
        ficheDePaie.setSalaireImposable(Math.round(salaireImposable * 1000.0) / 1000.0);
        ficheDePaie.setIrppMensuel(Math.round(irppAnnuelle * 1000.0) / 1000.0);
        ficheDePaie.setCssMensuel(Math.round(cssAnnuelle * 1000.0) / 1000.0);
        ficheDePaie.setSalaireNet(Math.round(salaireNet * 1000.0) / 1000.0);
        ficheDePaie.setDateAjouter(currentDate);
        ficheDePaie.setSecteurPrive(SecteurPrive);
        ficheDePaie.setMatricule(matricule);

        ficheDePaie.setNomEntreprise(NomEntreprise);
        ficheDePaie.setAdresseEntreprise(AdresseEntreprise);
        ficheDePaie.setAffiliationCss(AffiliationCss);
        ficheDePaie.setCinEmploye(CinEmploye);
        ficheDePaie.setNomEmploye(NomEmploye);
        ficheDePaie.setAdresse(Adresse);
        ficheDePaie.setNCss(NCss);
        ficheDePaie.setSituationFamiliale(SituationFamiliale);
        ficheDePaie.setFonction(Fonction);
        salaireRepo.save(ficheDePaie);

        return "Salaire calculé et enregistré avec succès.";
    }

    public List<FicheDePaie> getAllFicheDePaie() {
        return salaireRepo.findAll();
    }
}