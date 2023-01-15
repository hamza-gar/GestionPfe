package com.example.gestionpfe.Shared;

import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    static String htmlHeader = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <title>Registration Verification</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <h1>Welcome to Our Service!</h1>\n" +
            "  <p>Thanks for registering with us. Please click the link below to verify your email address and complete your registration.</p>\n" +
            "  <a href=\'http://localhost:8080/etudiants/verification/";

    static String htmlFooter = "\'>Verify Email Address</a>\n" +
            "  <p>If the link above does not work, please copy and paste the following URL into your browser:</p>\n" +
            "  <p>{{verificationLink}}</p>\n" +
            "  <p>If you did not request to register, please ignore this email.</p>\n" +
            "  <p>Best regards,</p>\n" +
            "  <p>The Team</p>\n" +
            "</body>\n" +
            "</html>";

    public void sendVerificationMail(String toEmail, String token) {


        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Verification");
            helper.setText(htmlHeader + token + htmlFooter, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Mail send ....");
    }
}