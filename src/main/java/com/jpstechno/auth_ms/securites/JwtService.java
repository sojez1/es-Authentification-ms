package com.jpstechno.auth_ms.securites;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;

import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret-key}")
    private final String secret_key;

    public String generateAccessToken(ActeurEcoles acteur) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", acteur.getActeur().getEmailPersonnel());
        claims.put("nom", acteur.getActeur().getPrenom() + " " + acteur.getActeur().getNom().substring(0, 1));
        claims.put("school_id", acteur.getEcole().getId());
        claims.put("role", acteur.getFonction());

        return Jwts.builder().header().and()
                .subject(acteur.getId().toString())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + SecurityParams.DUREE_ACCESS_TOKEN))
                .signWith(getKey())
                .compact();

    }

    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();

    }

    public String extractUserId(String token) {
        return extractClaims(token).getSubject();
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
