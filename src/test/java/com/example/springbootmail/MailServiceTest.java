package com.example.springbootmail;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    @Test
    public void sayHelloTest(){
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailMessageTest(){
        mailService.sendSimpleMailMessage("1274637575@qq.com","朱鹏程","chinese");
    }

    @Test
    public void sendHtmlMailMessageTest() throws MessagingException {
        String content = "<html>\n" + "<body>\n" + "<h1>THAILAND</h1>\n" + "</body>\n" + "</html>";
        mailService.sendHtmlMailMessage("1274637575@qq.com","周圣菲",content);
    }

    @Test
    public void sendAttachMailMessageTest() throws MessagingException {
//        String filePath = "D:\\java_workstation\\springboot-mail\\helloworld.docx";
        String filePath = "D:\\java_workstation\\springboot-mail\\login.PNG";
        mailService.sendAttachMailMessage("1274637575@qq.com","朱鹏程","图片",filePath);
    }

    @Test
    public void sendPictureMailMessageTest() throws MessagingException {
        String filePath = "D:\\java_workstation\\springboot-mail\\login.PNG";
        String fileId = "login";
        String content = "<html>\n" + "<body>\n" + "<img src=cid:" + fileId + ">" + "</img>" +
                "</body>\n" + "</html>";
        mailService.sendPictureMailMessage("1274637575@qq.com","朱鹏程",content,filePath,fileId);
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        Context context = new Context();
        String content = templateEngine.process("mail",context);
        mailService.sendHtmlMailMessage("1274637575@qq.com","朱鹏程",content);
    }
}
