package com.jpstechno.auth_ms.modeles;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    private String nomEcole;

    private String adresseEcole;

    private boolean isEcoleActif;

    private String logoEcole; // URL ou chemin vers le logo de l'école

    @OneToMany(mappedBy = "ecole")
    private List<ActeurEcoles> listeActeurEcole; // relation avec la liste des acteurs de l'ecole
}
