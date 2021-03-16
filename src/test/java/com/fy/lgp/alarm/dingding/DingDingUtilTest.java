package com.fy.lgp.alarm.dingding;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

/**
 * author : li guang ping
 * description :
 * date : 21-3-16 上午10:33
 **/
public class DingDingUtilTest {

    @Test
    public void send() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            DingDingUtil.send(
                httpClient,
                "",
                "",
                ""
            );
        }

    }
}
