package com.example.Vaccine.Distribution.System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    public void generateMail(String to, String sub, String text){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,"UTF-8");
        try {
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setSubject(sub);
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
