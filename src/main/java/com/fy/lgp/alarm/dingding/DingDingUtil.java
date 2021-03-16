package com.fy.lgp.alarm.dingding;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.fy.lgp.alarm.util.KeyUtil;
import com.fy.lgp.alarm.util.ListUtil;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * author : li guang ping
 * description : 企业微信工具类  已转成钉钉
 * date : 20-7-29 上午11:25
 **/
public class DingDingUtil {
    private static final String ALL_AT = "@all";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON_TYPE = "application/json; charset=utf-8";
    private static final String TEXT_TYPE = "text";

    /**
     * 发送报警消息
     *
     * @param httpClient httpClient
     * @param hook    钉钉机器人的hook
     * @param content    消息
     * @param phone      要通知人的手机
     * @throws IOException
     */
    public static void send(HttpClient httpClient, String hook, String content, String phone) throws IOException {
        At at = new At();
        if (ALL_AT.equals(phone)) {
            at.setAtAll(true);
        } else {
            String[] split = phone.split(KeyUtil.SYMBOL_REGEX_OR);
            List<String> userPhones = Arrays.asList(split);
            at.addMentionedMobileList(userPhones);
        }
        send(httpClient, hook, content, at);
    }


    /**
     * 发送报警消息
     *
     * @param httpClient httpClient
     * @param hook    企业微信的hook
     * @param content    消息
     * @param at         要通知人的手机
     * @throws IOException
     */
    public static void send(HttpClient httpClient, String hook, String content, At at) throws IOException {
        HttpPost httppost = new HttpPost(hook);
        httppost.addHeader(CONTENT_TYPE, JSON_TYPE);
        StringEntity se = new StringEntity(encapsulationMessage(content, at), KeyUtil.UTF_8);
        httppost.setEntity(se);
        HttpResponse response = httpClient.execute(httppost);
        int statusCode = response.getStatusLine()
                                 .getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new RuntimeException("企业微信发送错误, " + EntityUtils.toString(response.getEntity()));
        }
    }

    /**
     * 封装消息
     *
     * @return
     */
    public static String encapsulationMessage(String content, At at) {
        WxMessage wxMessage = new WxMessage();
        wxMessage.setMsgtype(TEXT_TYPE);
        Text text = new Text();
        text.setContent(content);
        wxMessage.setText(text);
        wxMessage.setAt(at);
        return JSONArray.toJSON(wxMessage).toString();
    }

    public static class WxMessage implements Serializable {
        private String msgtype;
        private Text text;
        private At at;

        public String getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(String msgtype) {
            this.msgtype = msgtype;
        }

        public Text getText() {
            return text;
        }

        public void setText(Text text) {
            this.text = text;
        }

        public At getAt() {
            return at;
        }

        public void setAt(At at) {
            this.at = at;
        }
    }

    public static class Text implements Serializable {
        /**
         * 发送的内容
         */
        private String content;


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * 提醒人
     */
    public static class At implements Serializable {
        /**
         * 提醒人的手机号列表
         */
        private List<String> atMobiles;
        /**
         * 是否@All
         */
        @JSONField(name = "isAtAll")
        private boolean atAll = false;


        public void addMentionedMobileList(List<String> userList) {
            if (ListUtil.isEmpty(userList)) {
                setAtAll(true);
            } else {
                setAtMobiles(userList);
            }
        }

        public List<String> getAtMobiles() {
            return atMobiles;
        }

        public void setAtMobiles(List<String> atMobiles) {
            this.atMobiles = atMobiles;
        }

        public boolean isAtAll() {
            return atAll;
        }

        public void setAtAll(boolean atAll) {
            this.atAll = atAll;
        }
    }

}
