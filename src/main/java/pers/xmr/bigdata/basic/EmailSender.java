package pers.xmr.bigdata.basic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
    private static void sendTextMail(EmailSendInfo mailInfo) {

        // 判断是否需要身份认证
        EmailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        //【调试时使用】开启Session的debug模式
        sendMailSession.setDebug(false);
        try {
            // 根据session创建一个邮件消息
            MimeMessage mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);

            String toaddr=mailInfo.getToAddress();
            String ccaddr=mailInfo.getCcAddress();
            mailMessage.addRecipients(Message.RecipientType.TO,toaddr);
            mailMessage.addRecipients(Message.RecipientType.CC,ccaddr);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject(), "UTF-8");
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent, "UTF-8");

            // 发送邮件
            Transport.send(mailMessage);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }


    /**
     * 给定邮件主题和内容发送邮件
     *
     * @param title   邮件主题
     * @param content 邮件内容
     */
    public void sendMail(String title, String content) {
        EmailSendInfo mailInfo = new EmailSendInfo();
        String host = Property.getProperty("host");
        String username = Property.getProperty("username");
        String password = Property.getProperty("password");
        String sendEmailUsers = Property.getProperty("sendEmailUsers");
        String ccEmialUsers = Property.getProperty("ccEmailUsers");
        mailInfo.setMailServerHost(host);
        mailInfo.setValidate(true);
        mailInfo.setUserName(username);
        mailInfo.setPassword(password);
        mailInfo.setFromAddress(username);
        mailInfo.setToAddress(sendEmailUsers);
        mailInfo.setCcAddress(ccEmialUsers);
        mailInfo.setSubject(title);
        mailInfo.setContent(content);
        //发送文体格式邮件
       sendTextMail(mailInfo);
    }

}
