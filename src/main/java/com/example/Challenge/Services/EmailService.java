package com.example.Challenge.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    private Random random = new Random();

    public void sendEmail(String to, String subject, String body, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quicknotes2008@gmail.com");
        message.setTo(to);
        message.setSubject("Hello " + name + " !");
        message.setText("We just received the following message from you: " + body + "\n\n We will respond to you as quickly as possible. Thank you for your feedback!");

        mailSender.send(message);
    }

    public void sendOTP(String to, int otp){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quicknotes2008@gmail.com");
        message.setTo(to);
        message.setSubject("OTP Verification");

        message.setText(
                "Your OTP code is: " + otp + "\n\n" +
                        "Thank you so much for signing up with us!\n" +
                        "You will be getting all the notes mailed after publishing.\n\n" +
                        "— Team QuickNotes"
        );

        mailSender.send(message);


    }
}
