package verify;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public void send(String toEmail, String verifyCode) {
        //provide sender's email ID
        String fromEmail = "flm.g5.se1709@gmail.com";
        String fromPassword = "jsxwtlpphwdbtsog";
        //provide Mailtrap's host address
        String host = "smtp.gmail.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        //create the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);

            //set From email field
            message.setFrom(new InternetAddress(fromEmail));

            //set To email field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            //set email subject field
            message.setSubject("OTP verification code!");

            //set the content of the email message
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid black; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Your OTP verification code is</h1>\n"
                    + "      <h3></h3>\n"
                    + "      <small\n"
                    + "        >------------------------------------------------------------------</small>\n"
                    + "      <div\n"
                    + "        style=\"\n"
                    + "          background-color: rgb(171, 168, 168);\n"
                    + "          border-radius: 10px;\n"
                    + "          padding: 10px;\n"
                    + "          width: 30%;\n"
                    + "          margin: 20px auto;\n"
                    + "          letter-spacing: 10px;\">\n"
                    + "        <h1>"+verifyCode+"</h1>\n"
                    + "      </div>\n"
                    + "      <small\n"
                    + "        >---------------------------------------------------------------------</small\n"
                    + "      >\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:9999/Project_FLM/home\">link login</a>\n"
                    + "      </button>\n"
                    + "      <h5><a href=\"\">Thanks you</a></h5>\n"
                    + "    </div>",
                     "text/html");

            //send the email message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}