package com.jpstechno.auth_ms.modeles.Dto;

import java.time.LocalDate;

import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.modeles.Ecoles;
import com.jpstechno.auth_ms.modeles.Fonctions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EcoleActeurDto {

    private Ecoles ecole;

    private Acteurs acteurs;

    private String password;

    private LocalDate dateDernierPassword;

    private String emailInstitutionnel;

    private boolean emailVerified;

    private Fonctions fonction;

    private boolean isActeurEcoleActif;

}
