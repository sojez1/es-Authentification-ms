/**
 * 
 */

package com.jpstechno.auth_ms.services;

import java.util.List;

import com.jpstechno.auth_ms.modeles.Ecoles;
import com.jpstechno.auth_ms.modeles.Dto.EcoleActeurDto;

public interface EcoleServ {

    Ecoles EnregistrerEcole(EcoleActeurDto ecoleActeur);

    Ecoles modifierEcole(Long id, Ecoles ecole);

    String activerOuDesactiverEcole(long id);

    List<Ecoles> rechercherEcolesParNomOuId(String idOuNomEcole);

    void supprimerEcole(Long id);

    List<Ecoles> getAllEcoles(); // pour webmaster, voir la liste de toutes les ecoles

}
