package com.LabTest.LabTest.entity;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String to, String subject, String content) {
        // Sender's email ID needs to be mentioned
        String from = "MS_hesWdK@trial-z3m5jgry1km4dpyo.mlsender.net";
        final String username = "MS_hesWdK@trial-z3m5jgry1km4dpyo.mlsender.net"; // your Gmail username
        final String password = "tRR955UdMztdPaQ7"; // your Gmail password

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.mailersend.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(content);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
