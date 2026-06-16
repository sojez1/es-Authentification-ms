package com.jpstechno.auth_ms.securites;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String secret_key;

    public JwtService(@Value("${jwt.secret-key}") String secret) {
        this.secret_key = secret;
    }

    public String generateAccessToken(ActeurEcoles acteurEcole) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", acteurEcole.getActeur().getEmailPersonnel());
        claims.put("nom", acteurEcole.getActeur().getPrenom() + " " + acteurEcole.getActeur().getNom().substring(0, 1));
        claims.put("school_id", acteurEcole.getEcole().getId());
        claims.put("role", acteurEcole.getFonction());

        return Jwts.builder().header().and()
                .subject(acteurEcole.getId().toString())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + SecurityParams.DUREE_ACCESS_TOKEN * 60 * 1000))
                .signWith(getKey())
                .compact();
    }

    public boolean isTokenValide(String token, MyUserPrincipal principal) {

        // 1- verifier si le token est expire et retournee true si expire
        if (isTokenExpired(token)) {
            return false;
        }

        // 2- verifier le claims
        return (extractUserId(token) == principal.getActeurId()) && (extractSchoolId(token) == principal.getSchoolId());
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();

    }

    public long extractUserId(String token) {
        String subject = extractClaims(token).getSubject();
        return Long.parseLong(subject);
    }

    public long extractSchoolId(String token) {
        return extractClaims(token).get("school_id", Long.class);
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
