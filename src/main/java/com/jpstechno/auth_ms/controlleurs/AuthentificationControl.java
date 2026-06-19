package com.jpstechno.auth_ms.controlleurs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.auth_ms.dto.LoginRequest;
import com.jpstechno.auth_ms.securites.JwtService;
import com.jpstechno.auth_ms.securites.MyUserPrincipal;
import com.jpstechno.auth_ms.securites.MyUsernamePasswordAuthenticationToken;
//import com.jpstechno.auth_ms.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/authentifier")
@RequiredArgsConstructor
public class AuthentificationControl {

    private final AuthenticationManager authManager;
    private final JwtService jwtServ;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication auth = authManager.authenticate(
                new MyUsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(),
                        loginRequest.getEcoleId()));

        if (auth.isAuthenticated()) {
            MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
            return jwtServ.generateAccessToken(principal.getConnectedActeurEcole());
        } else {
            return null;

        }

    }

    @PostMapping("/login/otp")
    public String verifyOtp(String otp) {
        return null;
    }

    @PostMapping("/password/forget/request")
    public String passwordForgetRequest(String usernameOrEmail, long schoolId) {

        return null;
    }

    @GetMapping("/password/reset/{id}/{token}")
    public String resetActeurEcolePassword(long id, String token, String newPassword) {
        return null;

    }

    @PostMapping("/refresh")
    public String refreshToken(String expiredAccessToken) {
        return null;

    }

    @GetMapping("/logtest")
    public String logtest() {
        System.out.println("LOGTEST APPELE");
        return "OK JP";
    }

}
