package com.jpstechno.auth_ms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpstechno.auth_ms.modeles.UrlVerification;

public interface UrlVerificationRepos extends JpaRepository<UrlVerification, Long> {

    @Query("SELECT urlverif FROM UrlVerification urlverif WHERE urlverif.id = :id AND urlverif.verificationToken = :token")
    Optional<UrlVerification> rechercherParId_Token(long id, String token);
}
