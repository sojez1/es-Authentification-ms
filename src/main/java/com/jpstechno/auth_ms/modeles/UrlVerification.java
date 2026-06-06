package com.jpstechno.auth_ms.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UrlVerification {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Acteurs acteurEcole;

    private String verificationToken;

    private boolean isUsed;

    private long expirationTime; // Durée de validité du token en millisecondes

}
