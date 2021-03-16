package com.fy.lgp.alarm.mail;

import java.util.List;

/**
 * author : li guang ping
 * description : 邮件
 * date : 20-9-2 下午5:06
 **/
public class Email {

    /**
     * 发送邮件的服务器的IP
     */
    private String mailServerHost;
    /**
     * 发送邮件的服务器的端口
     */
    private String mailServerPort;
    /**
     * 用户
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 发给谁
     */
    private String sendTo;
    /**
     * 抄送给谁
     * 多人以 | 分隔
     */
    private String ccTo;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 内容
     */
    private List<String> filePaths;

    public Email(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public Email(String sendTo, String subject, String content) {
        this(subject, content);
        this.sendTo = sendTo;
    }

    public Email(String mailServerHost, String mailServerPort, String sendTo, String subject, String content) {
        this(sendTo, subject, content);
        this.mailServerHost = mailServerHost;
        this.mailServerPort = mailServerPort;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getCcTo() {
        return ccTo;
    }

    public void setCcTo(String ccTo) {
        this.ccTo = ccTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }
}
