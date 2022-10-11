package org.registration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailSender {

    private static Logger log = LogManager.getLogger(EmailSender.class);
    private static String Subejct = "Added into Database";
    private final static String username = AppProperties.getProperties().getProperty("email.username");
    private final static String password = AppProperties.getProperties().getProperty("email.password");
    private static String host = AppProperties.getProperties().getProperty("email.host");
    private static Session newSession = null;
    private static String sender;
    private static MimeMessage mimeMessage;


    public static void sendEmail(User user) {
        sender = AppProperties.getProperties().getProperty("email.sender");
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "2525");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        newSession = Session.getDefaultInstance(properties,null);
        mimeMessage = new MimeMessage(newSession);

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(sender));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            // Set Subject: header field
            message.setSubject(Subejct);
            // Put the content of your message
            message.setText(getMainMessage(user));
            // Send message
            Transport.send(message);
            log.trace("Sent message successfully to {}", user.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getMainMessage(User user){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The user  " + user.getName() + " Has been added into the Database \n");
        stringBuffer.append("Details: \n");
        stringBuffer.append("Name: " + user.getName() + "\n");
        stringBuffer.append("Last name: " + user.getLastName() + "\n");
        stringBuffer.append("Email: " + user.getEmail() + "\n");
        stringBuffer.append("City: " + user.getCity() + "\n");
        stringBuffer.append("Zip Code: " + user.getZipCode());

        return stringBuffer.toString();

    }


}
