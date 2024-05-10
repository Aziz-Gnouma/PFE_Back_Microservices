package net.codejava.api.Repo;

import net.codejava.api.entity.Category;
import net.codejava.api.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByEntreprise(String entrepriseNom);

    List<Formation> findByCategoriesId(Long id);

    @Query("SELECT f FROM Formation f JOIN f.categories c WHERE c.name = :categoryName")
    List<Formation> findByCategoryName(@Param("categoryName") String categoryName);
    @Query("SELECT f FROM Formation f JOIN f.categories c WHERE c.name = :categoryName AND f.entreprise = :entreprise")
    List<Formation> findByCategoryNameAndEntreprise(@Param("categoryName") String categoryName, @Param("entreprise") String entreprise);


}
