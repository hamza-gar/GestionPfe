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

    private static String type;

    HtmlMessageSender htmlMessageSender = new HtmlMessageSender();
    String htmlHeader = htmlMessageSender.htmlHeader;
    String htmlFooter = htmlMessageSender.htmlFooter;
    String Header = htmlMessageSender.Header;
    String Body = htmlMessageSender.htmlBody;

    public void sendVerificationMail(String toEmail, String token,String type) {


        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Verification");
            helper.setText(Header+htmlHeader +type+"/verification/"+ token + Body +htmlFooter, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Mail send ....");
    }
}