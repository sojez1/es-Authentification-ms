package com.jpstechno.auth_ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.auth_ms.modeles.UrlVerification;

public interface UrlVerificationRepos extends JpaRepository<UrlVerification, Long> {

}
