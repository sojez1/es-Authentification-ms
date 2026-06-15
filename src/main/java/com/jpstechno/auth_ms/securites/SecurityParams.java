package com.jpstechno.auth_ms.securites;

import java.util.List;

public class SecurityParams {

    static final List<String> URL_AUTORISES = List.of(
            "http://jpstechno.com",
            "http://localhost");

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

    public static final long DUREE_ACCESS_TOKEN = 15; // duree access token en minute

}
