package com.nov.sendmail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * 使用qq邮箱发送邮件，邮件内容包含text和image(硬编码方式)
 * 实际使用最好配置化，非SendMail重点。使用配置请见/src/resources/demo.properties & ../util/PropertyUtil,MailConstants）。
 * @author HONY
 * @date 2018/11/14
 */
public class SendMail {
    /**
     qq邮箱SMTP服务参数
     设置方法见：https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=14&&no=1000898
     */
    private static final String PROTOCOL = "SMTP";
    private static final String HOST = "smtp.qq.com";
    private static final String PORT = "465";

    private static final String USERNAME = "123456789@qq.com";
    private static final String PASSWD = "123456789";
    private static final String RECIPIENT = "987654321@qq.com";

    private static final String EMAIL_MSG = "<h2 style=\"color:red\">good! from qq mail</h2><img src='cid:pic.jpg'>";


    public static void main(String[] args) {

        // 设置连接属性
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host",HOST);
        props.setProperty("mail.smtp.port",PORT);
        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.smtp.timeout","1000");

        /* 开启SSL证书，QQ邮箱必须。163等不需要*/
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.ssl.socketFactory",mailSSLSocketFactory);
        /* SSL证书设置完毕*/

        // 设置账号密码
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME,PASSWD);
            }
        };

        // 创建session
        Session session = Session.getInstance(props,auth);
        session.setDebug(true);
        // 设置邮件内容
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(RECIPIENT));
            message.setSubject("邮件发送测试");
            message.setSentDate(new Date());
            // 组装邮件内容
            MimeMultipart mimeMultipart = new MimeMultipart();
            // 文本
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(EMAIL_MSG,"text/html;charset=utf-8");
            mimeMultipart.addBodyPart(text);
            // 图片 位置在src/
            MimeBodyPart image = new MimeBodyPart();
            DataHandler dataHandler = new DataHandler(new FileDataSource("src/pic.jpg"));
            image.setDataHandler(dataHandler);
            image.setContentID("pic.jpg");
            mimeMultipart.addBodyPart(image);
            mimeMultipart.setSubType("related");

            message.setContent(mimeMultipart);
            message.saveChanges();
            /* 保存一份到本地
            message.writeTo(new FileOutputStream("D:\\IMAGE_EMAIL.eml"));*/
            // 发送邮件
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
