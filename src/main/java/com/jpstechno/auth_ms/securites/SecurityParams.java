package com.jpstechno.auth_ms.securites;

import java.util.List;

public class SecurityParams {

        public static final long DUREE_ACCESS_TOKEN = 15; // duree access token en minute

        static final List<String> URL_AUTORISES = List.of(
                        "http://jpstechno.com",
                        "http://localhost:5173");

        static final List<String> ENTETES_AUTORISES = List.of(
                        "Authorization",
                        "Content-Type",
                        "Accept",
                        "Origin",
                        "X-Requested-With");

        static final List<String> METHODES_AUTORISEES = List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS");

        static final String[] PUBLIC_URLs = {
                        "/authentifier/logtest",
                        "/authentifier/login",
                        "/acteurs/enregistrer/nouveau",
                        "/ecoles/utilisateurs/ajouter",
                        "/error" };

}
