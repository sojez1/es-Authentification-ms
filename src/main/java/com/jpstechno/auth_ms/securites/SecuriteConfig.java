package com.jpstechno.auth_ms.securites;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecuriteConfig {

    private final MyAuthenticationProvider myAuthenticationProvider;
    private final JwtFilter jwtFilter;

    SecuriteConfig(MyAuthenticationProvider myAuthenticationProvider, JwtFilter filter) {
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.jwtFilter = filter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/authentifier/logtest",
                                "/authentifier/login",
                                "/acteurs/enregistrer/nouveau",
                                "/ecoles/utilisateurs/ajouter",
                                "/error")
                        .permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(myAuthenticationProvider)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfig() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(SecurityParams.URL_AUTORISES);
        cors.setAllowedHeaders(SecurityParams.ENTETES_AUTORISES);
        cors.setAllowedMethods(SecurityParams.METHODES_AUTORISEES);
        cors.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlCorsSource = new UrlBasedCorsConfigurationSource();
        urlCorsSource.registerCorsConfiguration("/**", cors);

        return urlCorsSource;
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) {
        return authConfig.getAuthenticationManager();

    }

}
