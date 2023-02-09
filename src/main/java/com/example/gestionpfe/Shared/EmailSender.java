package com.example.gestionpfe.Shared;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    String InvitationStateRefused2 = htmlMessageSender.InvitationRefuse2;
    String InvitationStateAccepted = htmlMessageSender.InvitationStateAccepted;
    String InvitationStateAccepted2 = htmlMessageSender.InvitationAccepte2;
    String SoutenancePostPoned1 = htmlMessageSender.SoutenancePostPoned1;
    String SoutenancePostPoned2 = htmlMessageSender.SoutenancePostPoned2;
    String SoutenancePostPoned3 = htmlMessageSender.SoutenancePostPoned3;
    String SoutenancePostPoned4 = htmlMessageSender.SoutenancePostPoned4;
    String RendezVousPris1 = htmlMessageSender.RendezVousPris1;
    String RendezVousPris2 = htmlMessageSender.RendezVousPris2;
    String RendezVousPris3 = htmlMessageSender.RendezVousPris3;
    String RendezVousFixed1 = htmlMessageSender.RendezVousFixed;
    String RendezVousFixed2 = htmlMessageSender.RendezVousFixed2;
    String RendezVousFixed3 = htmlMessageSender.RendezVousFixed3;
    String RendezVousFixed4 = htmlMessageSender.RendezVousFixed4;
    String RendezVousNonPrisEt = htmlMessageSender.RendezVousNonPrisEt;
    String RendezVousNonPrisEns1 = htmlMessageSender.RendezVousNonPrisEns1;
    String RendezVousNonPrisEns2 = htmlMessageSender.RendezVousNonPrisEns2;
    String DemandeRendezVous = htmlMessageSender.DemandeRendezVous;
    String DemandeRendezVous2 = htmlMessageSender.DemandeRendezVous2;
    String ResetPassword1 = htmlMessageSender.ResetPassword1;
    String ResetPassword2 = htmlMessageSender.ResetPassword2;
    String ShareLienDrive1 = htmlMessageSender.ShareLienDrive1;
    String ShareLienDrive2 = htmlMessageSender.ShareLienDrive2;
    String ShareLienDrive3 = htmlMessageSender.ShareLienDrive3;

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
    public void invitationToJoinGroup(String toEmail, String fromEmail, String password, String studentName) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + InvitationToGroup1 + fromEmail + InvitationToGroup2 + password + GroupPassword + studentName + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }

    //share google drive link
    /*TODO : get link*/
    public void shareGDriveLink(String toEmail, String link) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + shareDriveLink1 + link + shareDriveLink2 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


    //invitation accepted
    public void InvitationAccepted(String toEmail, String emailInvite) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + InvitationStateAccepted + emailInvite + InvitationStateAccepted2 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


    //soutenance refused
    public void InvitationRefused(String toEmail, String emailInvite) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Invitation Refusee");
            helper.setText(Header + InvitationStateRefused + emailInvite + InvitationStateRefused2 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


    //postPoned email
    public void PostPonedEmail(String toEmail, String sujet, Date date) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String pattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Nouvelle notification");
            helper.setText(Header + SoutenancePostPoned1 + sujet + SoutenancePostPoned2 + simpleDateFormat.format(date) + SoutenancePostPoned3 + simpleTimeFormat.format(date) + SoutenancePostPoned4 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }

    public void RendezVousFixed(String toEmail, String emailProf, Date date) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String pattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Rendez-vous fixé par l'encadrant");
            helper.setText(Header + RendezVousFixed1 + emailProf + RendezVousFixed2 + simpleDateFormat.format(date) + RendezVousFixed3 + simpleTimeFormat.format(date) + RendezVousFixed4 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Mail send ....");
    }


    public void RendezVousPris(String toEmail, Date date) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String pattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Rendez-vous pris");
            helper.setText(Header + RendezVousPris1 + simpleDateFormat.format(date) + RendezVousPris2 + simpleTimeFormat.format(date) + RendezVousPris3 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Mail send ....");
    }

    public void RendezVousNonPrisEt(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String pattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Rendez-vous non pris");
            helper.setText(Header + RendezVousNonPrisEt + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Mail send ....");
    }

    public void RendezVousNonPrisEns(String toEmail, String sujet) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String pattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Rendez-vous non pris");
            helper.setText(Header + RendezVousNonPrisEns1 + sujet + RendezVousNonPrisEns2 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Mail send ....");
    }

    public void DemandeRendezVous(String toEmail, String sujet) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Demande Rendez-Vous");
            helper.setText(Header + DemandeRendezVous + sujet + DemandeRendezVous2 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Mail send ....");
    }

    public void ForgottenPassword(String toEmail, String key) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Demande Rendez-Vous");
            helper.setText(Header + ResetPassword1 + key + ResetPassword2 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Mail send ....");
    }

    public void ShareLienDriveJury(String toEmail, Date date, String lien) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String pattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Demande Rendez-Vous");
            helper.setText(Header + ShareLienDrive1 + lien + ShareLienDrive2 + simpleDateFormat.format(date) + " a " + simpleTimeFormat.format(date) + ShareLienDrive3 + htmlFooter, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Mail send ....");
    }


}