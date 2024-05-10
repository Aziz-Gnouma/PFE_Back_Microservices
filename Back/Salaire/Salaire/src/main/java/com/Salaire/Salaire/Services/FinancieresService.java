package com.Salaire.Salaire.Services;
import com.Salaire.Salaire.Repository.AgenceRepository;
import com.Salaire.Salaire.Repository.BankRepository;
import com.Salaire.Salaire.Repository.FinancieresRepository;
import com.Salaire.Salaire.entity.Agence;
import com.Salaire.Salaire.entity.AgenceDTO;
import com.Salaire.Salaire.entity.Bank;
import com.Salaire.Salaire.entity.Financieres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancieresService {
    @Autowired
    private FinancieresRepository financieresRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AgenceRepository AgenceRepository;

    public Financieres saveFinancieresWithBankAndAgency(Financieres financieres, String bankName, String agencyName) {
        // Find or create bank entity
        Bank bank = bankRepository.findByName(bankName);
        if (bank == null) {
            bank = new Bank();
            bank.setName(bankName);
            bank = bankRepository.save(bank);
        }

        // Find or create agency entity
        Agence agency = AgenceRepository.findByNameAndBank(agencyName, bank);
        if (agency == null) {
            agency = new Agence();
            agency.setName(agencyName);
            agency.setBank(bank);
            agency = AgenceRepository.save(agency);
        }

        financieres.setBank(bank);
        financieres.setAgency(agency);
        Date currentDate = new Date();
        financieres.setDateAjouter(currentDate);

        return financieresRepository.save(financieres);
    }

    public List<Financieres> getAllFinancieresByMatricule(String matricule) {
        return financieresRepository.findByMatricule(matricule);
    }
    public List<Financieres> getLastFinancieresByMatricule(String matricule) {
        return financieresRepository.findByMatriculeAndLatestDateAjouter(matricule);
    }
    public String formatAgencies(List<AgenceDTO> agencies) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < agencies.size(); i++) {
            result.append("{\"name\": \"").append(agencies.get(i).getName()).append("\"}");
            if (i < agencies.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    public List<AgenceDTO> getAllAgenciesOfBank(String bankName) {
        return AgenceRepository.findByBank_Name(bankName)
                .stream()
                .map(agency -> new AgenceDTO(agency.getName()))
                .collect(Collectors.toList());
    }
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
    public Bank getBankByName(String bankName) {
        return bankRepository.findByName(bankName);
    }

    public Bank getOrCreateBank(String bankName) {
        Bank bank = getBankByName(bankName);
        if (bank == null) {
            // Bank not found, create new bank
            bank = new Bank();
            bank.setName(bankName);
            bank = bankRepository.save(bank);
        }
        return bank;
    }


}
