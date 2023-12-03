//package com.mentalhealth.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Component;
//
///**
// * @Classname MailUtils
// * @Description 邮件发送工具类
// * @Date 2019-3-14 16:25
// * @Created by CrazyStone
// */
//@Component
//public class MailUtils {
//    @Autowired
//    private JavaMailSenderImpl mailSender;
//    @Value("${spring.mail.username}")
//    private String mailfrom;
//
//    // 发送简单邮件
//    public void sendSimpleEmail(String mailto, String title, String content) {
//        //  定制邮件发送内容
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(mailfrom);
//        message.setTo(mailto);
//        message.setSubject(title);
//        message.setText(content);
//        // 发送邮件
//        mailSender.send(message);
//    }
//}
//
