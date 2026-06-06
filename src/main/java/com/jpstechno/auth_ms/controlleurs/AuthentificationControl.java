package com.jpstechno.auth_ms.controlleurs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.auth_ms.dto.LoginRequest;
//import com.jpstechno.auth_ms.services.AuthenticationService;
import com.jpstechno.auth_ms.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/authentifier")
@RequiredArgsConstructor
public class AuthentificationControl {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // return AuthenticationService.authenticate(loginRequest);
        return "Login successful";
    }

    @GetMapping("/logtest")
    @PreAuthorize("permitAll()")
    public String loginTest() {
        return authenticationService.loginTest();
    }

}
