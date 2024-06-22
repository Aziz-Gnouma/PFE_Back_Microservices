package org.vaadin.marcus.service;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vaadin.marcus.data.Reclamation;

import java.util.Optional;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    Optional<Reclamation> findByReclamationIdAndMatriculeAndFirstNameAndLastNameIgnoreCase(
            Long reclamationId, String matricule, String firstName, String lastName
    );

    @Query("SELECT COUNT(u) FROM Reclamation u")
    long countAllReclamations();
}