package com.example.springbootmail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String sender;

    public void sayHello(){
        System.out.println("你好");
    }

    @Autowired
    JavaMailSender javaMailSender;

    //发送简单邮件
    public void sendSimpleMailMessage(String to,String subject,String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }

    //发送html邮件
    public void sendHtmlMailMessage(String to,String subject,String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setText(content,true);
        mimeMessageHelper.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }

    //发送附件邮件
    public void sendAttachMailMessage(String to,String subject,String content,String filePath) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        String fileName = fileSystemResource.getFilename();
        mimeMessageHelper.addAttachment(fileName,fileSystemResource);
        javaMailSender.send(mimeMessage);
    }

    //发送内置图片邮件 背景
    public void sendPictureMailMessage(String to,String subject,String content,String filePath,String fileId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setText(content,true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        mimeMessageHelper.addInline(fileId,fileSystemResource);
        javaMailSender.send(mimeMessage);
    }
}
