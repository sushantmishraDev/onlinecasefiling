package com.dms.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.xml.serialize.HTMLdtd;
import org.springframework.mail.MailSender;  
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;  
   
public class MailMail{  
    private JavaMailSender mailSender;  
   
    public void setMailSender(JavaMailSender mailSender) {  
        this.mailSender = mailSender;  
    }  
   
    public void sendMail(String from, String to, String subject, String msg) {  
        //creating message  
       /* SimpleMailMessage message = new SimpleMailMessage();  
        message.setFrom(from);  
        message.setTo(to);  
        message.setSubject(subject);  
        message.setText(msg);  
        //sending message  
        mailSender.send(message);*/     
        
        try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = msg;
        //mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        helper.setText(htmlMsg, true); // Use this or above line.
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);
        mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(HTMLdtd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}  
