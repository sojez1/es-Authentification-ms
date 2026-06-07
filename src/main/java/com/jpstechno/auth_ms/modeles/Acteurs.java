package com.jpstechno.auth_ms.modeles;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeActeur", discriminatorType = jakarta.persistence.DiscriminatorType.STRING)
@Entity
public class Acteurs {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long acteurId;

    private String nom;

    private String prenom;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private Sexes sexe;

    @NaturalId
    private String emailPersonnel;

    private String telephone;

    private boolean isActif;

    private boolean isEmailVerified;

    @OneToMany(mappedBy = "acteur")
    private List<ActeurEcoles> listeEcoleDeActeur; // Liste des ecoles ou l'acteur est enregistree

    public Acteurs() {
        this.listeEcoleDeActeur = new ArrayList<ActeurEcoles>();
    }

    // constructeur avec paramètres (sans id)
    public Acteurs(String nom, String prenom, String email, String telephone, String motDePasse, Ecoles ecole) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.emailPersonnel = email;
        this.telephone = telephone;

    }

    // constructeur avec paramètres (avec id)
    public Acteurs(long id, String nom, String prenom, String email, String telephone, String motDePasse,
            Ecoles ecole) {
        this(nom, prenom, email, telephone, motDePasse, ecole);
        this.acteurId = id;
    }
}
