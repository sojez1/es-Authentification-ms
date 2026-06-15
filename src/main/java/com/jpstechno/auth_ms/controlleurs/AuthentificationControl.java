package com.jpstechno.auth_ms.controlleurs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.auth_ms.dto.LoginRequest;
import com.jpstechno.auth_ms.securites.JwtService;
//import com.jpstechno.auth_ms.services.AuthenticationService;
import com.jpstechno.auth_ms.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/authentifier")
@RequiredArgsConstructor
public class AuthentificationControl {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authManager;
    private final JwtService jwtServ;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (auth.isAuthenticated()) {
            return jwtServ.generateAccessToken(null);
        } else {
            return null;
        }

    }

    @PostMapping("/refresh")
    public String refreshToken(String expiredAccessToken) {
        return null;

    }

    @GetMapping("/logtest")
    @PreAuthorize("permitAll()")
    public String loginTest() {
        return authenticationService.loginTest();
    }

}
