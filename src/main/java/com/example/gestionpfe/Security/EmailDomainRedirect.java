package com.example.gestionpfe.Security;

public class EmailDomainRedirect {
    public static String getRedirectUrl(String email) {
        String domain = email.substring(email.indexOf("@") + 1);

        if (domain.equals("gmail.com")) {
            return "/gmail";
        } else if (domain.equals("yahoo.com")) {
            return "/yahoo";
        } else {
            return "/other";
        }
    }
}
