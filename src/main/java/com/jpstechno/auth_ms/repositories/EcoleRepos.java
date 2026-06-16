package com.jpstechno.auth_ms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpstechno.auth_ms.modeles.Ecoles;

public interface EcoleRepos extends JpaRepository<Ecoles, Long> {

    @Query("update Ecoles e set e.isEcoleActif = :active where e.id = :id")
    boolean activerOuDesactiverEcole(Long id, boolean active);

    @Query("SELECT ecol FROM Ecoles ecol WHERE LOWER(ecol.nomEcole) LIKE LOWER(CONCAT('%',:denomination,'%'))  ")
    List<Ecoles> rechercheParDenomination(String denomination);

    Optional<Ecoles> findByEmailEcole(String emailEcole);

}
