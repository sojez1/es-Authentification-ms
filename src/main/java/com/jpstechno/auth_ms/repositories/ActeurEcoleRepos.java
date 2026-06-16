package com.jpstechno.auth_ms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;

public interface ActeurEcoleRepos extends JpaRepository<ActeurEcoles, Long> {

    @Query("SELECT actEco FROM ActeurEcoles actEco WHERE actEco.ecole.id = :schoolId AND LOWER(actEco.acteur.emailPersonnel) = LOWER(:email)")
    Optional<ActeurEcoles> findByEmailAndSchoolId(@Param("email") String email, @Param("schoolId") long schoolId);

    @Query("SELECT actEco FROM ActeurEcoles actEco WHERE actEco.acteur.acteurId = :userId AND actEco.ecole.id = :schoolId ")
    Optional<ActeurEcoles> findByActeurIdAndSchoolId(Long userId, Long schoolId);

}
