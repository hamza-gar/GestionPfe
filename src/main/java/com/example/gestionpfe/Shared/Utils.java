package com.example.gestionpfe.Shared;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

@Component
public class Utils {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateUserId(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public static String generateSecret() {
        SecureRandom random = new SecureRandom();
        byte[] secret = new byte[32];
        random.nextBytes(secret);
        return Base64.getEncoder().encodeToString(secret);
    }

//    public void sendMail(String destination, String token) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.mailgun.org");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("postmaster@sandbox49b63feace624ec49081dda2cb8c051f.mailgun.org", "e3d77df93ceddcfc476621c8d5a973b5-4c2b2223-47082536");
//                    }
//                });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("fstetouan.gestionpfe@uae.ac.ma"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(destination));
//            message.setSubject("GestionPFE Tetouan");
//            message.setText("<a href=\"localhost:8080/etudiants/verification/" + token + "\">Click Here !</a>");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
