package com.fy.lgp.alarm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : li guang ping
 * description : 数组工具类
 * date : 20-7-13 下午4:01
 **/
public class ListUtil {
    /**
     * 获取listString的regex化语句
     */
    public static String regexOrString(Collection<String> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(KeyUtil.SYMBOL_LEFT_BRACKET);
        for (String sub : collection) {
            stringBuilder.append(sub);
            stringBuilder.append("\\.");
            stringBuilder.append(KeyUtil.SYMBOL_OR);
        }
        if (stringBuilder.lastIndexOf(KeyUtil.SYMBOL_OR) != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(KeyUtil.SYMBOL_RIGHT_BRACKET);
        return stringBuilder.toString();
    }

    /**
     * 获取listString的sql化语句
     */
    public static <T> String sqlStringByCollection(Collection<T> collection) {
        List<String> stringList = new ArrayList<>();
        for (T sub : collection) {
            stringList.add(String.valueOf(sub));
        }
        return sqlString(stringList);
    }

    /**
     * 获取listString的sql化语句
     */
    public static String sqlString(Collection<String> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(KeyUtil.SYMBOL_LEFT_BRACKET);
        for (String sub : collection) {
            stringBuilder.append(KeyUtil.SINGLE_QUPTE);
            stringBuilder.append(sub);
            stringBuilder.append(KeyUtil.SINGLE_QUPTE);
            stringBuilder.append(KeyUtil.SYMBOL_COMMA);
        }
        if (stringBuilder.lastIndexOf(KeyUtil.SYMBOL_COMMA) != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(KeyUtil.SYMBOL_RIGHT_BRACKET);
        return stringBuilder.toString();
    }


    /**
     * 获取listString的sql化语句
     */
    public static String orString(Collection<String> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String sub : collection) {
            stringBuilder.append(sub);
            stringBuilder.append(KeyUtil.SYMBOL_OR);
        }
        if (stringBuilder.lastIndexOf(KeyUtil.SYMBOL_OR) != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取listString的sql化语句
     */
    public static String sqlIntString(Collection<Integer> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(KeyUtil.SYMBOL_LEFT_BRACKET);
        for (Integer sub : collection) {
            stringBuilder.append(sub);
            stringBuilder.append(KeyUtil.SYMBOL_COMMA);
        }
        if (stringBuilder.lastIndexOf(KeyUtil.SYMBOL_COMMA) != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(KeyUtil.SYMBOL_RIGHT_BRACKET);
        return stringBuilder.toString();
    }

    /**
     * 数组为空
     */
    public static boolean isEmpty(Collection input) {
        return input == null || input.size() == 0;
    }

}
