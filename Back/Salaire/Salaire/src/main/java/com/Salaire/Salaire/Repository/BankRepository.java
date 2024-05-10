package com.Salaire.Salaire.Repository;

import com.Salaire.Salaire.entity.Bank;
import com.Salaire.Salaire.entity.Financieres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByName(String bankName);
}
