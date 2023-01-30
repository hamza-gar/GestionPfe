package com.example.gestionpfe.Shared;

public class HtmlMessageSender {
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
            "          </tr>\n" ;
    static String validationHeader =
            "          <tr>\n" +
            "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "                <tr>\n" +
            "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Bienvenue dans notre service !</h1>\n" +
            "                    <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +
            "Merci de vous être inscrit. Veuillez cliquer sur le bouton ci-dessous pour vérifier votre adresse e-mail et terminer votre inscription.</p>\n" +
            "                    <p style=\"margin-left:50%;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">"+
            "  <a href='http://localhost:8080/";


    static String validationFooter ="'style=\"color:#ee4c50;text-decoration:underline; background-color: #ee4c50; /* or any other color */\n" +
            "    color: white; /* text color */\n" +
            "    padding: 10px 20px; /* button size */\n" +
            "    border-radius: 5px; /* button corners */\n" +
            "    text-decoration: none; \">Vérifier</a></p>\n" +
            "                  </td>\n" +
            "                </tr>\n" +
            "            \n" +
            "<tr>\n" +"<td>\n"+"<p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n" +"\n" +
            "Si vous n'avez pas demandé à vous inscrire, veuillez ignorer cet e-mail.\n" +
            "\n" +
            "</p>"+"<p>"+"Cordialement\n"+"</p>"+"<p>"+"L'équipe\n"+"</p>"+"</td>"+"</tr>\n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr>\n" ;

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

}