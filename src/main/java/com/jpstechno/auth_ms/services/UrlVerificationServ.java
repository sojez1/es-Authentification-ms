package com.jpstechno.auth_ms.services;

import com.jpstechno.auth_ms.modeles.Acteurs;

import jakarta.mail.MessagingException;

public interface UrlVerificationServ {

    public void generateVerificationUrl(Acteurs acteur) throws MessagingException;

}
