package com.jpstechno.auth_ms.securites;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class OtpFilter extends AbstractAuthenticationProcessingFilter {

    public OtpFilter(AuthenticationManager authManager) {
        super("/login/otp");
        setAuthenticationManager(authManager);

    }

}
