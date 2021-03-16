package com.fy.lgp.alarm.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * author : li guang ping
 * description : 字符串处理
 * date : 20-9-3 上午11:37
 **/
public class StringUtil {
    /**
     * 文本转邮件正文
     *
     * @param string
     * @return
     */
    public static final String nextLine2MailBr(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                                 "<html>\n" +
                                 "<head>\n" +
                                 "<meta charset=\"utf-8\">\n" +
                                 "<title>一个邮箱的网页</title>\n" +
                                 "</head>\n" +
                                 " \n" +
                                 "<body>");
        String[] split = string.split(KeyUtil.SYMBOL_NEXT_LINE);
        stringBuilder.append("<div>");
        for (String subSplit : split) {
            stringBuilder.append(subSplit);
            stringBuilder.append(KeyUtil.HTML_NEXT_LINE);
        }
        stringBuilder.append("</div>");

        stringBuilder.append("</body>\n" +
                                 " \n" +
                                 "</html>");
        return stringBuilder.toString();
    }


    /**
     * 将多个对象字符串
     *
     * @return
     */
    public static String unionString(Object... objects) {
        if (objects == null) {
            return KeyUtil.EMPTY_STRING;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Object o : objects) {
                if (o != null) {
                    stringBuilder.append(o);
                    stringBuilder.append(KeyUtil.SYMBOL_OR);
                }
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            return stringBuilder.toString();
        }
    }

    /**
     * 字符串转md5
     *
     * @param text
     * @return
     */
    public static String md5(String text){
        try{
            return DigestUtils.md5Hex(getContentBytes(text));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取utf的字符串数组
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static byte[] getContentBytes(String text) throws Exception {
        return text.getBytes(KeyUtil.UTF_8);
    }


}
