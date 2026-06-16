package com.jpstechno.auth_ms.modeles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ecoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vous devez saisir la denomination de l'ecole")
    private String nomEcole;

    @NotBlank(message = "Vous devez indiquer l'adresse de l'ecole")
    private String adresseEcole;

    @NotBlank(message = "Vous devez indiquer un numero de telephone pour l'ecole, incluant l'indicatif pays")
    @Column(unique = true)
    private String telephoneEcole;

    @NotBlank(message = "Vous devez indiquer un email de contact pour votre ecole")
    @Email(message = "l'adresse email doit etre syntaxiquement correcte")
    @Column(unique = true)
    private String emailEcole;

    private boolean isEcoleActif;

    private String logoEcole; // URL ou chemin vers le logo de l'école

    @OneToMany(mappedBy = "ecole")
    @JsonIgnore
    private List<ActeurEcoles> listeActeurEcole; // relation avec la liste des acteurs de l'ecole
}
