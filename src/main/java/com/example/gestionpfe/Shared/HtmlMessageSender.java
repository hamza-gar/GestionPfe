package com.example.gestionpfe.Shared;

public class HtmlMessageSender {
    // email de verification email
    /***********************************************************************************************************************************************************************************************************************************************************************/
    static String Header = "<!DOCTYPE html>\n" +
            "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
            "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
            "  <title></title>\n" +
            "  <!--[if mso]>\n" +
            "  <noscript>\n" +
            "    <xml>\n" +
            "      <o:OfficeDocumentSettings>\n" +
            "        <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
            "      </o:OfficeDocumentSettings>\n" +
            "    </xml>\n" +
            "  </noscript>\n" +
            "  <![endif]-->\n" +
            "  <style>\n" +
            "    table, td, div, h1, p {font-family: Arial, sans-serif;}\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body style=\"margin:0;padding:0;\">\n" +
            "  <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;background:#ffffff;\">\n" +
            "    <tr>\n" +
            "      <td align=\"center\" style=\"padding:0;\">\n" +
            "        <table role=\"presentation\" style=\"width:602px;border-collapse:collapse;border:1px solid #cccccc;border-spacing:0;text-align:left;\">\n" +
            "          <tr>\n" +
            "            <td align=\"center\" style=\"padding:40px 0 30px 0;background:#70bbd9;\">\n" +
            "              <img src=\"https://upload.wikimedia.org/wikipedia/fr/thumb/7/7d/Universit%C3%A9_Abdelmalek_Essa%C3%A2di.png/1200px-Universit%C3%A9_Abdelmalek_Essa%C3%A2di.png\" alt=\"\" width=\"300\" style=\"height:auto;display:block;\" />\n" +
            "            </td>\n" +
            "          </tr>\n";
    static String validationHeader =
            "          <tr>\n" +
                    "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
                    "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
                    "                <tr>\n" +
                    "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
                    "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Bienvenue dans notre service !</h1>\n" +
                    "                    <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
                    "Merci de vous être inscrit. Veuillez cliquer sur le bouton ci-dessous pour vérifier votre adresse e-mail et terminer votre inscription.</p>\n" +
                    "                    <p style=\"margin-left:50%;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">" +
                    "  <a href='http://localhost:8080/";


    static String validationFooter = "'style=\"color:#ee4c50;text-decoration:underline; background-color: #ee4c50; /* or any other color */\n" +
            "    color: white; /* text color */\n" +
            "    padding: 10px 20px; /* button size */\n" +
            "    border-radius: 5px; /* button corners */\n" +
            "    text-decoration: none; \">Vérifier</a></p>\n" +
            "                  </td>\n" +
            "                </tr>\n" +
            "            \n" +
            "<tr>\n" + "<td>\n" + "<p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" + "\n" +
            "Si vous n'avez pas demandé à vous inscrire, veuillez ignorer cet e-mail.\n" +
            "\n" +
            "</p>" + "<p>" + "Cordialement\n" + "</p>" + "<p>" + "L'équipe\n" + "</p>" + "</td>" + "</tr>\n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr>\n";

    static String htmlFooter =
            "          <tr>\n" +
                    "            <td style=\"padding:30px;background:#ee4c50;\">\n" +
                    "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;font-size:9px;font-family:Arial,sans-serif;\">\n" +
                    "                <tr>\n" +
                    "                  <td style=\"padding:0;width:50%;\" align=\"left\">\n" +
                    "                  </td>\n" +
                    "                  <td style=\"padding:0;width:50%;\" align=\"right\">\n" +
                    "                    <table role=\"presentation\" style=\"border-collapse:collapse;border:0;border-spacing:0;\">\n" +
                    "                      <tr>\n" +
                    "                        <td style=\"padding:0 0 0 10px;width:38px;\">\n" +
                    "                          <a href=\"http://www.twitter.com/\" style=\"color:#ffffff;\"><img src=\"https://assets.codepen.io/210284/tw_1.png\" alt=\"Twitter\" width=\"38\" style=\"height:auto;display:block;border:0;\" /></a>\n" +
                    "                        </td>\n" +
                    "                        <td style=\"padding:0 0 0 10px;width:38px;\">\n" +
                    "                          <a href=\"http://www.facebook.com/\" style=\"color:#ffffff;\"><img src=\"https://assets.codepen.io/210284/fb_1.png\" alt=\"Facebook\" width=\"38\" style=\"height:auto;display:block;border:0;\" /></a>\n" +
                    "                        </td>\n" +
                    "                      </tr>\n" +
                    "                    </table>\n" +
                    "                  </td>\n" +
                    "                </tr>\n" +
                    "              </table>\n" +
                    "            </td>\n" +
                    "          </tr>\n" +
                    "        </table>\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </table>\n" +
                    "</body>\n" +
                    "</html>";

    /***********************************************************************************************************************************************************************************************************************************************************************/

    //Email notification chaque jour à 6h du matin
    static String BodyMessage =
            "          <tr>\n" +
                    "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
                    "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
                    "                <tr>\n" +
                    "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
                    "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Bienvenue dans notre service !</h1>\n" +
                    "                    <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n";
    static String BodyMessagePart2 =
            " </p>\n" +
                    "                    <p style=\"margin-left:50%;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">" +
                    "</p>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "            \n" +
                    "<tr>\n" + "<td>\n" + "<p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" + "\n";
    static String BodyMessagePart3 =
            "</p>" + "<p>" + "Cordialement\n" + "</p>" + "<p>" + "L'équipe\n" + "</p>" + "</td>" + "</tr>\n" +
                    "              </table>\n" +
                    "            </td>\n" +
                    "          </tr>\n";

    /***********************************************************************************************************************************************************************************************************************************************************************/


    static String VoteHeader =
            "          <tr>\n" +
                    "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
                    "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
                    "                <tr>\n" +
                    "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
                    "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Rendes Vous </h1>\n" +
                    "                    <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
                    "J'ai choisi cette date pour notre prochaine réunion, est-ce que ça vous convient ?<b>\n";


    static String RefuseRendesVous = "</b></p>\n" +

            "<tr>\n" + "<td>\n" + "<p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" + "\n" +
            "si cela est possible pour vous, nous allons le faire, sinon nous choisirons une autre date.\n" +
            "\n" +
            "</p>" + "<p>" + "Cordialement\n" + "</p>" + "<p>" + "L'équipe\n" + "</p>" + "</td>" + "</tr>\n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr>\n";

    /***********************************************************************************************************************************************************************************************************************************************************************/

    static String invitation1 = "          <tr>\n" +
            "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "                <tr>\n" +
            "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\"> Invitation à être jury ! </h1>\n" +
            "                    <p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "Nous avons le plaisir de vous inviter en tant que ";
    static String invitation2 = " à la soutenance de notre projet intitulée %s qui aura lieu le ";
    static String invitation3 = ".</p>\n" +
            "                    <p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "Veuillez accepter ou refuser cette invitation en cliquant sur l'un des boutons ci-dessous :</p>\n" +
            "                    <p style=\"margin-left:20%;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">";

    static String invitationLink = "  <a href='http://localhost:8080/jurys/validate?idInvitation=";

    static String invitationAccept = "&accept=true'style=\"color:#ee4c50;text-decoration:underline; background-color: #ee4c50; /* or any other color */\n" +
            "    color: white; /* text color */\n" +
            "    padding: 10px 20px; /* button size */\n" +
            "    border-radius: 5px; /* button corners */\n" +
            "    text-decoration: none; \">Accepter</a>\n";

    static String invitationRefuse = "&accept=false'style=\"color:#ee4c50;text-decoration:underline; background-color: #ee4c50; /* or any other color */\n" +
            "    color: white; /* text color */\n" +
            "    padding: 10px 20px; /* button size */\n" +
            "    border-radius: 5px; /* button corners */\n" +
            "    text-decoration: none; \">Refuser</a></p>\n";

    static String invitation4 =
            "                  </td>\n" +
                    "                </tr>\n" +
                    "            \n" +
                    "<tr>\n" + "<td>\n" + "<p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" + "\n" +
                    "Nous vous remercions pour votre temps et votre collaboration.\n" +
                    "\n" +
                    "Cordialement,\n" + "</p>" + "<p>" + "L'équipe\n" + "</p>" + "</td>" + "</tr>\n" +
                    "              </table>\n" +
                    "            </td>\n" +
                    "          </tr>\n";

    /***********************************************************************************************************************************************************************************************************************************************************************/

    static String shareDriveLink1 = "<tr>\n" +
            "          <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "            <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "              <tr>\n" +
            "                <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                  <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Salutations</h1>\n" +
            "                  <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Ceci est un message automatisé provenant d'un système programmé.\n" +
            "                    Le but de ce message est de vous informer qu'un nouveau fichier a été téléchargé sur notre plateforme\n" +
            "                    de stockage partagé, Google Drive. Le fichier contient des informations importantes relatives à notre projet.\n" +
            "                    Pour accéder au fichier, veuillez suivre le lien ci-dessous :\n" +
            "                  </p>\n" +
            "                  <p style=\"margin-left:50%;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "\n" +
            "  <a href='http://localhost:8080/";

    static String shareDriveLink2 = "style=\"color: blue; text-decoration: none; transition: all 0.3s ease-in-out; display: block; margin: 0 auto; text-align: left; margin-left: 10px;\">Drive Link</a>\n" +
            "\n" +
            "                  </p>\n" +
            "\n" +
            "                </td>\n" +
            "              </tr>\n" +
            "              <tr>\n" +
            "                <td>\n" +
            "                  <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Si vous rencontrez des difficultés pour accéder au fichier, veuillez contacter notre équipe de support pour obtenir de l'aide.\n" +
            "                    Merci de votre attention à ce sujet.\n" +
            "                  </p>\n" +
            "                  <p>Cordialement</p>\n" +
            "                  <p>L'équipe</p>\n" +
            "                </td>\n" +
            "              </tr>";

    /***********************************************************************************************************************************************************************************************************************************************************************/
    static String InvitationToGroup1 = "<tr>\n" +
            "          <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "            <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "              <tr>\n" +
            "                <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                  <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Invitation Privé</h1>\n" +
            "                  <p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Vous avez été invité par ";

    static String InvitationToGroup2 = " pour rejoindre une équipe de projet formée par des étudiants.\n" +
            "                    Nous sommes ravis de vous compter parmi nous et espérons que vous serez en mesure de contribuer à ce projet passionnant.\n" +
            "                  </p>\n" +
            "                  <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                  Pour vous connecter à notre plateforme de travail en équipe, veuillez utiliser le mot de passe suivant :\n" +
            "                  </p>\n" +
            "                  <p style=\"text-align: center; font-family: Arial, sans-serif; color: blue; font-size: 30px;\">";
    static String GroupPassword = "</p>\n" +
            "                  <p style=\"text-align: justify; margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Nous sommes impatients de travailler avec vous et de tirer le meilleur parti de votre expertise et de vos connaissances.\n" +
            "                    N'hésitez pas à nous contacter si vous avez des questions ou des préoccupations.\n" +
            "                  </p>\n" +
            "                  <p>Cordialement</p>\n" +
            "                  <p>";
    static String studentName = "</p>\n" +
            "                </td>\n" +
            "              </tr>";

    /***********************************************************************************************************************************************************************************************************************************************************************/

    static String InvitationStateRefused ="<tr>\n" +
            "          <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "            <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "              <tr>\n" +
            "                <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                  <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">état d'invitation</h1>\n" +
            "                  <p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    votre invitation a été refusée.\n" +
            "                  </p>\n" +
            "                  <h1><p>\uD83D\uDE14</p></h1>\n" +
            "                  <p>Cordialement.</p>\n" +
            "                  <p>L'equipe.</p>\n" +
            "                </td>\n" +
            "              </tr>";
    static String InvitationStateAccepted="<tr>\n" +
            "          <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "            <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "              <tr>\n" +
            "                <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                  <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">état d'invitation</h1>\n" +
            "                  <p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    votre invitation a été acceptée.\n" +
            "                  </p>\n" +
            "                  <h1><p>\uD83D\uDE04</p></h1>\n" +
            "                  <p>Cordialement.</p>\n" +
            "                  <p>L'equipe.</p>\n" +
            "                </td>\n" +
            "              </tr>";
    /***********************************************************************************************************************************************************************************************************************************************************************/
    static String SoutenancePostPoned1 = " <tr>\n" +
            "          <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "            <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "              <tr>\n" +
            "                <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                  <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Report de Soutenance</h1>\n" +
            "                  <p style=\"text-align: justify;margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Cher/Chère etudiants\n" +
            "                  </p>\n" +
            "                  <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Je vous écris pour vous informer que Soutenance prévu pour";
    static String SoutenancePostPoned2 = "a été reporté.\n" +
            "                  </p>\n" +
            "                  <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">Nous sommes désolés pour tout inconvénient que cela peut vous causer,\n" +
            "                    mais des considérations imprévues ont rendu nécessaire ce report.\n" +
            "                    Nous travaillons actuellement sur de nouvelles dates et nous vous en informerons dès que possible.</p>\n" +
            "                  <p style=\" margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "                    Nous vous remercions pour votre compréhension et nous nous excusons encore une fois pour tout désagrément causé.\n" +
            "                  </p>\n" +
            "                  <p>Cordialement</p>\n" +
            "                  <p>Enseignat</p>\n" +
            "                </td>\n" +
            "              </tr>";
}
