package com.fy.lgp.alarm.mail;


import com.fy.lgp.alarm.util.KeyUtil;
import com.fy.lgp.alarm.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * author : li guang ping
 * description : 简单邮件发送通用类
 * date : 20-9-2 下午5:06
 **/
public class SimpleEmailUtil {

    private static final String TEXT_UTF_8 = "text/html; charset=utf-8";

    public static void sendEmail(Email email) {
        try {
            MailSenderInfo mailInfo = getMailInfo(email);
            // 这个类主要来发送邮件
            sendMail(mailInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 以文本格式发送邮件
     *
     * @param mailInfo 待发送的邮件的信息
     */
    private static boolean sendMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        try {
            Address from = new InternetAddress(mailInfo.getFromAddress());

            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            List<String> ccAddresses = mailInfo.getCcAddresses();
            if (ccAddresses != null) {
                Address[] ccArray = new Address[ccAddresses.size()];
                for (int i = 0; i < ccAddresses.size(); i++) {
                    ccArray[i] = new InternetAddress(ccAddresses.get(i));
                }
                mailMessage.setRecipients(Message.RecipientType.CC, ccArray);
            }
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());

            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart multipart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(StringUtil.nextLine2MailBr(mailInfo.getContent()), TEXT_UTF_8);
            multipart.addBodyPart(html);
            List<String> filePaths = mailInfo.getFilePaths();
            if (filePaths != null) {
                for (String path : filePaths) {
                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(path);
                    String[] split = path.split(KeyUtil.SYMBOL_OBLIQUE_LINE);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    //MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(split[split.length - 1]));
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(multipart);
            //保存邮件
            mailMessage.saveChanges();
            // 发送邮件
            Transport.send(mailMessage, mailMessage.getAllRecipients());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private static MailSenderInfo getMailInfo(Email email) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        String user = email.getUser();
        mailInfo.setMailServerHost(email.getMailServerHost());
        mailInfo.setMailServerPort(email.getMailServerPort());
        mailInfo.setUserName(user);
        mailInfo.setPassword(email.getPassword());
        String ccTo = email.getCcTo();
        if (ccTo != null) {
            mailInfo.updateCcAddresses(ccTo);
        }
        mailInfo.setFromAddress(user);
        mailInfo.setToAddress(email.getSendTo());
        mailInfo.setSubject(email.getSubject());
        mailInfo.setContent(email.getContent());
        mailInfo.setFilePaths(email.getFilePaths());
        return mailInfo;
    }
}
