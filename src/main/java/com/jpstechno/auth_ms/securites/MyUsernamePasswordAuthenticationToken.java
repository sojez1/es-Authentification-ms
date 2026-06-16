package com.jpstechno.auth_ms.securites;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class MyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private long schoolId;

    public MyUsernamePasswordAuthenticationToken(MyUserPrincipal principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities, long schoolId) {
        super(principal, credentials, authorities);
        this.schoolId = schoolId;
    }

    public MyUsernamePasswordAuthenticationToken(String email, String password, long schoolId) {
        super(email, password);
        this.schoolId = schoolId;
    }

    public MyUserPrincipal getUserPrincipal() {
        return (MyUserPrincipal) getPrincipal();
    }

    public long getSchoolId() {
        return schoolId;
    }

}