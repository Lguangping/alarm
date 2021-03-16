package com.fy.lgp.alarm.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * author : li guang ping
 * description : 身份验证
 * date : 20-9-2 下午4:26
 **/
public class MyAuthenticator extends Authenticator {
    private String username;
    private String password;
    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,  password);
    }
}
