package com.jpstechno.auth_ms.services;

import com.jpstechno.auth_ms.dto.LoginRequest;

public interface AuthenticationService {

    String authenticate(LoginRequest loginRequest);

    String loginTest();

    String resetPassword(String email, long schoolID);

}
