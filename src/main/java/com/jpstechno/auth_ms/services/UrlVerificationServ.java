/**
 * 
 * @Author: Jean-Pierre Sovi Guidi
 */

package com.jpstechno.auth_ms.services;

import com.jpstechno.auth_ms.modeles.Acteurs;

import jakarta.mail.MessagingException;

public interface UrlVerificationServ {

    /**
     * Methode permettant de generer un lien de validation
     * 
     * @param acteur acteur pour qui on genere le lien de validation
     * @param chemin url path ( mapping au niveau du controlleur )du lien de
     *               validation (ex: /acteurs/verification).
     * 
     */
    public String generateVerificationUrl(Acteurs acteur, String chemin) throws MessagingException;

    /**
     * Methode permettant d'envoyer le lien de verification a l'utilisateur concerne
     * 
     * @param acteur             Utilisateur (de type Acteurs) a qui on veut envoyer
     *                           le lien
     * @param lienDeVerification lien de verification pour cet utiliateur
     */
    public void sendVerificationLink(Acteurs acteur, String lienDeVerification);

    /**
     * Methode permettant de generer puis d'envoyer un lien de validation a
     * l'utilisateur concerne
     * 
     * @param acteur utilisateur (de type Acteurs) a qui on desire envoyer un lien
     *               de verification
     * @param chemin url path ( mapping au niveau du controlleur )du lien de
     *               validation (ex: /acteurs/verification).
     */
    public void generateAndSendVerificationLink(Acteurs acteur, String chemin);

}
