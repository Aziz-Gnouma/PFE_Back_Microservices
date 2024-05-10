package com.Salaire.Salaire.Repository;

import com.Salaire.Salaire.entity.Agence;
import com.Salaire.Salaire.entity.Bank;
import com.Salaire.Salaire.entity.Financieres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

    Agence findByNameAndBank(String agencyName, Bank bank);

    List<Agence> findByBank(Bank bank);

    List<Agence> findByBankId(Long id);

    List<Agence> findByBankName(String bankName);

    List<Agence> findByBank_Name(String bankName);
}
