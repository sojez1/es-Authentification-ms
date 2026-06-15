package com.jpstechno.auth_ms.services;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.modeles.Acteurs;

public interface ActeurEcoleServ {

    ActeurEcoles saveUserForSchool(ActeurEcoles acteurEcole);

    String modifierPassword(String email, long school_id, String oldPassword, String newPassord);

    String desactiverUtilisateur(long id);

    /**
     * Permet a un promoteur existant d'ajouter d'autres utilisateur (acteur) comme
     * promoteur
     * d'une ecole
     * 
     * @param promoteur promoteur a ajouter a une ecole
     * @param school_id identifiant de l'ecole pour laquelle on veut ajouter le
     *                  promoteur
     * @return message confirmant si le promoteur est bien ajouter ou non
     */
    String ajouterPromoteur(Acteurs promoteur, long school_id);

    /**
     * Permet a un promoteur d'une ecole de se retirer de la liste des promoteur
     * 
     * @param promoteur_id id utilisateur du promoteur qui veut se retirer
     * @param school_id    id de l'ecole dont il veut se retirer ex promoteur
     * @return message de confirmation si le retrait est effectif ou non.
     */
    String retirerPromoteur(long promoteur_id, long school_id);

}
