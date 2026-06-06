package com.jpstechno.auth_ms.modeles;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cette classe permet d'associer un acteur a une ecole
 * Sachant qu'un acteur peut intervenir dans plusieurs ecoles et qu'une ecole
 * peut avoir plusieurs acteurs
 * Chaque acteur se connecte a son ecole
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { // contrainte unicite acteur + ecole
        @UniqueConstraint(name = "uk_acteur_ecole", columnNames = { "acteur_id", "ecole_id" })
})
public class ActeurEcoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acteur_id")
    private Acteurs acteur;

    @ManyToOne
    @JoinColumn(name = "ecole_id")
    private Ecoles ecole;

    private String password;

    private LocalDate dateDernierPassword;

    private String emailInstitutionnel;

    private boolean isActeurEcoleActif;

    private Fonctions fonction;

}
