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

    static String htmlHeader = "<!DOCTYPE html>\n" +
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
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
            "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
            "                <tr>\n" +
            "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
            "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Creating Email Magic</h1>\n" +
            "                    <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan et dictum, nisi libero ultricies ipsum, posuere neque at erat.</p>\n" +
            "                    <p style=\"margin-left:30%;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">"+
            "  <a href='http://localhost:8080/";

    static String htmlFooter = "'style=\"color:#ee4c50;text-decoration:underline; background-color: #ee4c50; /* or any other color */\n" +
            "    color: white; /* text color */\n" +
            "    padding: 10px 20px; /* button size */\n" +
            "    border-radius: 5px; /* button corners */\n" +
            "    text-decoration: none; \">In tempus felis blandit</a></p>\n" +
            "                  </td>\n" +
            "                </tr>\n" +
            "            \n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td style=\"padding:30px;background:#ee4c50;\">\n" +
            "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;font-size:9px;font-family:Arial,sans-serif;\">\n" +
            "                <tr>\n" +
            "                  <td style=\"padding:0;width:50%;\" align=\"left\">\n" +
            "                    <p style=\"margin:0;font-size:14px;line-height:16px;font-family:Arial,sans-serif;color:#ffffff;\">\n" +
            "                      &reg; Someone, Somewhere 2021<br/><a href=\"http://www.example.com\" style=\"color:#ffffff;text-decoration:underline;\">Unsubscribe</a>\n" +
            "                    </p>\n" +
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



    public void sendVerificationMail(String toEmail, String token,String type) {


        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Gestion PFE : Verification");
            helper.setText(htmlHeader +type+"/verification/"+ token + htmlFooter, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Mail send ....");
    }
}