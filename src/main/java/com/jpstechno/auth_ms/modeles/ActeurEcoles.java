package com.jpstechno.auth_ms.modeles;

import java.time.LocalDate;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "L'utilisateur ne doit pas etre null")
    @Valid
    private Acteurs acteur;

    @ManyToOne
    @JoinColumn(name = "ecole_id")
    @NotNull(message = "L'ecole ne doit pas etre nulle")
    @Valid
    private Ecoles ecole;

    @NotBlank(message = "le mot de passe ne doit pas etre vide")
    @Size(min = 8, message = "Le mot de passe doit avoir au mooins 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$", message = "le mot de passe doit contenir au moins 1 majuscule, 1 miniscule, 1 chiffre et 1 caractere special")
    private String password;

    private LocalDate dateDernierPassword;

    @NaturalId(mutable = true)
    private String emailInstitutionnel;

    private boolean isActeurEcoleActif;

    @NotNull(message = "vous devez indiquer la fonction ou le role de cet utilisateur")
    private Fonctions fonction;

    @PrePersist
    public void valeurParDefaut() {
        dateDernierPassword = LocalDate.now();
    }
}
