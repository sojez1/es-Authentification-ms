package com.jpstechno.auth_ms.utilitaires;

public class MessageTemplates {

    public static String nouveauActeurMail(String prenom, String verificationUrl) {
        return """
                <h2>Bonjour %s</h2>
                <p>Votre compte a été crée avec succes</p>
                <p>Afin d'activer votre compte, veuillez confirmer votre email en cliquant sur le lien ci-dessous</p>
                <p>
                    <a href="%s">cliquer ici pour confirmer votre email</a>
                </p>
                <p>Ce lien est valide pour une duree de %d minutes</p>

                    <p>Cordialement, <br>
                    <strong>Toute l'équipe de easyschool</strong>
                </p>

                    """.formatted(prenom, verificationUrl, ConstanteParams.dureeValiditeMessage);

    }

    public static String nouvelleEcoleMail() {
        return """

                """;
    }
}
