package com.example.authentication.dao;

import com.example.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCin(int cin);

    @Query("SELECT COUNT(u) FROM UserInfo u")
    long countAllUsers();

    @Query("SELECT COUNT(u) FROM UserInfo u WHERE u.role = :role")
    long countUsersByRole(@Param("role") String role);

    @Query("SELECT COUNT(u) FROM UserInfo u JOIN u.role r WHERE r.roleName = :roleName")
    long countUsersByRoleName(@Param("roleName") String roleName);
    boolean existsByMatricule(String matricule);
}
