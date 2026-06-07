package com.jpstechno.auth_ms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpstechno.auth_ms.modeles.Acteurs;

public interface ActeurRepos extends JpaRepository<Acteurs, Long> {

    @Query("SELECT act FROM Acteurs act WHERE LOWER(act.emailPersonnel) = LOWER(:email)")
    Optional<Acteurs> findByEmail(String email);

    @Query("""
            SELECT act
            FROM Acteurs act
            WHERE LOWER(act.nom) LIKE LOWER(CONCAT('%',:motCle,'%')) OR LOWER(act.prenom) LIKE LOWER(CONCAT('%',:motCle,'%'))
            """)
    List<Acteurs> rechercherParMotCle(String motCle);

}
