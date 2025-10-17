package com.dms.controller;

import java.util.Properties;

import org.springframework.beans.factory.*;  
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;  
public class Test {   
	

	
public static void main(String[] args) {  
      
//Resource r=new ClassPathResource("dms-context.xml"); 
	System.out.println(new ClassPathXmlApplicationContext());  
@SuppressWarnings("resource")
ApplicationContext ctx = new ClassPathXmlApplicationContext("mail-context.xml"); 

System.setProperty("http.protocols", "TLSv1,TLSv1.1,TLSv1.2");

MailMail m = (MailMail) ctx.getBean("mailMail");

String msg="Please find annexed mention notice below details of notice served to you";

/*BeanFactory b=new XmlBeanFactory(r);  
MailMail m=(MailMail)b.getBean("mailMail"); */ 
String sender="enotice@allahabadhighcourt.in";//write here sender gmail id  
String receiver="sushantmishra09@gmail.com";//write here receiver id  
m.sendMail(sender,receiver,"hi","http://localhost:8082/onlinecasefiling/");  
      
System.out.println("success");  
}  
}  
