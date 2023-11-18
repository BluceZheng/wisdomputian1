package com.haomeng.wisdomputian.util;

import java.util.UUID;

public class UUIDUtil {
    /**
     * 带-的UUID
     *
     * @return 36位的字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取id
     *
     * @return
     */
    public static String getID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 去掉-的UUID
     *
     * @return 32位的字符串
     */
    public static String getNoLineUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}