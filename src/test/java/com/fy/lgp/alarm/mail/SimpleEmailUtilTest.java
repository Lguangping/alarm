package com.fy.lgp.alarm.mail;

import org.junit.Test;

/**
 * author : li guang ping
 * description :
 * date : 21-3-16 上午10:33
 **/
public class SimpleEmailUtilTest {

    @Test
    public void sendEmail(){
        Email email = new Email(
            "",
            "",
            "",
            "",
            ""
        );
        email.setUser("");
        email.setPassword("");
        SimpleEmailUtil.sendEmail(email);
    }
}
