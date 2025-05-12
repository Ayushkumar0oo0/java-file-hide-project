package main.java.service;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class SendOTPService {
    public static void sendOTP(String toEmail, String otp) {
        final String fromEmail = "your gmail "; // use your gmail to send code to someone
        final String password = "gamil password";// use alternative 16 digit gmail password 

        // SMTP Configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Session Setup
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + otp);

           
            Transport.send(message);
            System.out.println("OTP sent successfully to: " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send OTP");
        }
    }
}
