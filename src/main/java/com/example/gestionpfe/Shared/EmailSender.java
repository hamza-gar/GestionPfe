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
    String VoteHeader = htmlMessageSender.VoteHeader;
    String invitation1 = htmlMessageSender.invitation1;
    String invitation2 = htmlMessageSender.invitation2;
    String invitation3 = htmlMessageSender.invitation3;
    String invitationLink = htmlMessageSender.invitationLink;
    String invitationAccept = htmlMessageSender.invitationAccept;
    String invitationRefuse = htmlMessageSender.invitationRefuse;
    String invitation4 = htmlMessageSender.invitation4;
    String InvitationToGroup1 = htmlMessageSender.InvitationToGroup1;
    String InvitationToGroup2 = htmlMessageSender.InvitationToGroup2;
    String GroupPassword = htmlMessageSender.GroupPassword;
    String studentName = htmlMessageSender.studentName;
    String shareDriveLink1 = htmlMessageSender.shareDriveLink1;
    String shareDriveLink2 = htmlMessageSender.shareDriveLink2;
    String InvitationStateRefused = htmlMessageSender.InvitationStateRefused;
    String InvitationStateAccepted = htmlMessageSender.InvitationStateAccepted;
    String SoutenancePostPoned1 = htmlMessageSender.SoutenancePostPoned1;
    String SoutenancePostPoned2 = htmlMessageSender.SoutenancePostPoned2;

    public void sendVerificationMail(String toEmail, String token, String type) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Verification");
            helper.setText(Header + htmlHeader + type + "/verification/" + token + Body + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }

    public void sendInvitationJury(String toEmail, String idSoutenance, String role, String date, String nomSujet) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Invitation a soutenance.");
            helper.setText(Header + invitation1 + role + String.format(invitation2, nomSujet) + date + invitation3 + invitationLink + idSoutenance + invitationAccept + invitationLink + idSoutenance + invitationRefuse + invitation4 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
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
            helper.setText(Header + NotificationBody + NotificationBodyPart2 + NotificationBodyPart3 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


    //invit student to join teams
    /*TODO : add list of emails to send to it and get the name of student who will send this email , and generate a private password */
    public void invitationToJoinGroup(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + InvitationToGroup1 +"student name who send invitation"+ InvitationToGroup2+"password generated" + GroupPassword + "student name who send invitation"+studentName+htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }

    //share google drive link
    /*TODO : get link*/
    public void shareGDriveLink(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + shareDriveLink1 +"link drive"+ shareDriveLink2 +htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


    //invitation accepted
    public void InvitationAccepted(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + InvitationStateAccepted +htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }



    //soutenance refused
    public void InvitationRefused(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + InvitationStateRefused +htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }




    //postPoned email
    public void PostPonedEmail(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + SoutenancePostPoned1 +"date"+SoutenancePostPoned2+htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }

//    public void RendezVous(String toEmail) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(toEmail);
//            helper.setSubject("Gestion PFE : Vous avez un rendezvous");
//            helper.setText(Header + VoteHeader + "Date" + RefuseRendesVous + htmlFooter, true);
//            mailSender.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        logger.info("Mail send ....");
//    }


}