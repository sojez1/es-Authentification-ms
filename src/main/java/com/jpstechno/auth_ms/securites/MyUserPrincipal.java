package com.jpstechno.auth_ms.securites;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyUserPrincipal implements UserDetails {

    private final ActeurEcoles connectedUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(connectedUser.getFonction().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return connectedUser.getPassword();
    }

    @Override
    public String getUsername() {
        return connectedUser.getActeur().getEmailPersonnel();
    }

    public long getSchoolId() {
        return connectedUser.getEcole().getId();
    }

    public long getActeurId() {
        return connectedUser.getActeur().getActeurId();
    }

    @Override
    public boolean isEnabled() {
        return connectedUser.isActeurEcoleActif() && connectedUser.getActeur().isActif()
                && connectedUser.getActeur().isEmailVerified();
    }

    public ActeurEcoles getConnectedActeurEcole() {
        return connectedUser;
    }

}
