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

    public static String nouvelleEcoleMail(String prenom, String denominationEcole, String urlVerification) {
        return """

                <h2>Bonjour %s</h2>
                    <p>Une nouvelle ecole denommee %s vient d'etre enregistrer dans le systeme easySchool</p>
                    <p>Vous email apparait comme promoteur de ladite ecole </p>
                    <p>
                        <a href="%s">cliquer ici pour confirmer que vous en etes bel et bien promoteur et activer l'ecole</a>
                    </p>
                    <p>Ce lien est valide pour une duree de %d minutes</p>

                        <p>Cordialement, <br>
                        <strong>Toute l'équipe de easyschool</strong>
                    </p>

                        """
                .formatted(prenom, denominationEcole, urlVerification, ConstanteParams.dureeValiditeMessage);

    }
}
