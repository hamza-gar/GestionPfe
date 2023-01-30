package com.example.gestionpfe.Shared;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {
    Logger logger = org.slf4j.LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private JavaMailSender mailSender;

    private static String type;

    static HtmlMessageSender htmlMessageSender = new HtmlMessageSender();
    String htmlHeader = htmlMessageSender.validationHeader;
    String htmlFooter = htmlMessageSender.htmlFooter;
    String Header = htmlMessageSender.Header;
    String Body = htmlMessageSender.validationFooter;
    String NotificationBody = htmlMessageSender.BodyMessage;
    String NotificationBodyPart2 = htmlMessageSender.BodyMessagePart2;
    String NotificationBodyPart3 = htmlMessageSender.BodyMessagePart3;
    String VoteHeader  = htmlMessageSender.VoteHeader;
    String RefuseRendesVous = htmlMessageSender.RefuseRendesVous;

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

        logger.info("Mail send ....");
    }


    public void notifyYOUAREINATEAM(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + NotificationBody +NotificationBodyPart2+NotificationBodyPart3+htmlFooter, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }

    public void RendesVous(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header+VoteHeader + "Date"+RefuseRendesVous+htmlFooter, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


}