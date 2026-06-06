package com.jpstechno.auth_ms.serviceImplementations;

import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.dto.LoginRequest;
import com.jpstechno.auth_ms.services.AuthenticationService;

@Service
public class AuthenticationImplementation implements AuthenticationService {

    @Override
    public String authenticate(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public String loginTest() {
        return "Login test successful";
    }

}
