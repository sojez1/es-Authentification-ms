package com.jpstechno.auth_ms.serviceImplementations;

import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.dto.LoginRequest;
import com.jpstechno.auth_ms.exceptions.acteurs.ActeurNotFoundException;
import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.repositories.ActeurEcoleRepos;
import com.jpstechno.auth_ms.repositories.ActeurRepos;
import com.jpstechno.auth_ms.services.AuthenticationService;
import com.jpstechno.auth_ms.services.UrlVerificationServ;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationImplementation implements AuthenticationService {

    final UrlVerificationServ urlServ;
    final ActeurEcoleRepos acteurRepo;

    @Override
    public String authenticate(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public String loginTest() {
        return "Login test successful";
    }

    @Override
    public String resetPassword(String email, long schoolID) {
        ActeurEcoles ae = acteurRepo.findByEmailAndSchoolId(email, schoolID).orElse(null);
        if (ae == null) {
            throw new ActeurNotFoundException();
        }

        String chemin = "/authentifier/password/reset/{id}/{token}";
        urlServ.generateAndSendVerificationLink(ae.getActeur(), chemin);
        return "Lien de reset du password envoye dans votre courriel";
    }

}
