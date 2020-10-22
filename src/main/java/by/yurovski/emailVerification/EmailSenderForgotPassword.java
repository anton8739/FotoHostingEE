package by.yurovski.emailVerification;

import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.exception.RegistrationException;
import by.yurovski.manager.PageContentManager;
import by.yurovski.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

public class EmailSenderForgotPassword {
    public static void sendMessage(User user) {
        String password1=user.getPassword();

        String text="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\"/>\n" +
                "    \n" +
                "</head>\n" +
                "<body>\n" +
                "<table class=\"body-wrap\">\n" +
                "    <tr>\n" +
                "        <td class=\"container\">\n" +
                "\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" class=\"masthead\">\n" +
                "\n" +
                "                        <h1>Password recovery on the site FotoHosting</h1>\n" +
                "\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"content\">\n" +
                "\n" +
                "                        <h2>Hello,<span >"+user.getLogin()+"</span></h2>\n" +
                "\n" +
                "                        <p>Password from your account </p>\n" +password1+
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"container\">\n" +
                "\n" +
                "            <!-- Message start -->\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td class=\"content footer\" align=\"center\">\n" +
                "                        <p>Sent by <a href=\"#\">FotoHosting by Yurovski</a>, 230023 Ozeshko Street, OZ, 99999</p>\n" +
                "                        <p><a href=\"mailto:\">anton87398739@gmail.com</a> </p>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        ResourceBundle resourceBundle=ResourceBundle.getBundle("email");
        String to = user.getEmail();

        String from = resourceBundle.getString("mail.support.username");
        final String username = resourceBundle.getString("mail.support.username");
        final String password = resourceBundle.getString("mail.support.password");

        String host = resourceBundle.getString("mail.smtp.host");
        String port = resourceBundle.getString("mail.smtp.port");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Forgot Passoword");

            // Put your HTML content using HTML markup
            message.setContent(text, "text/html; charset=utf-8");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {

        }
    }
}
