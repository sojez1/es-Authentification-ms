package com.jpstechno.auth_ms.securites;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jpstechno.auth_ms.exceptions.acteurs.ActeurNotFoundException;
import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.repositories.ActeurEcoleRepos;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final ActeurEcoleRepos acteurEcoleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        MyUsernamePasswordAuthenticationToken auth = (MyUsernamePasswordAuthenticationToken) authentication;
        String userName = auth.getName();
        String password = auth.getCredentials().toString();
        long schoolId = auth.getSchoolId();

        // recherche de l'utilisateur
        ActeurEcoles act = acteurEcoleRepo.findByEmailAndSchoolId(userName, schoolId)
                .orElseThrow(() -> new ActeurNotFoundException("Impossible de se connecter. Utilisateur non trouve"));

        // verification du mot de passe
        if (!passwordEncoder.matches(password, act.getPassword())) {
            throw new BadCredentialsException("Mot de passe incorrect");
        }

        MyUserPrincipal principal = new MyUserPrincipal(act);

        return new MyUsernamePasswordAuthenticationToken(principal, null,
                principal.getAuthorities(), schoolId);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
