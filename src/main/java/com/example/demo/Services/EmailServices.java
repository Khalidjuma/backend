package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage massage=new SimpleMailMessage();
        massage.setTo(to);
        massage.setSubject(subject);
        massage.setText(text);
        mailSender.send(massage);
    }
}
