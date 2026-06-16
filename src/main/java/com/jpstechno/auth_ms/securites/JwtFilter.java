package com.jpstechno.auth_ms.securites;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.repositories.ActeurEcoleRepos;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtServ;

    @Autowired
    ActeurEcoleRepos acteurEcolerepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestHeader = request.getHeader("Authorization");
        String tokenGottenFromRequest = null;
        Long userId = null;
        Long schoolId = null;

        // recuperation du token, du userId et du schoolId a partir du token recu dans
        // la requete
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            tokenGottenFromRequest = requestHeader.substring(7);
            userId = jwtServ.extractUserId(tokenGottenFromRequest);
            schoolId = jwtServ.extractSchoolId(tokenGottenFromRequest);
        }

        // Creation de l'objet d'authentification si

        if (userId != null && schoolId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            ActeurEcoles ae = acteurEcolerepo.findByActeurIdAndSchoolId(userId, schoolId).orElse(null);
            if (ae != null) {
                MyUserPrincipal principal = new MyUserPrincipal(ae);

                MyUsernamePasswordAuthenticationToken auth = new MyUsernamePasswordAuthenticationToken(principal, null,
                        principal.getAuthorities(), schoolId);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }

        filterChain.doFilter(request, response);
    }

}
