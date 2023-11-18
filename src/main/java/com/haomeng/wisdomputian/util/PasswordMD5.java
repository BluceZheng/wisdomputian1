package com.haomeng.wisdomputian.util;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

@Data
public class PasswordMD5 {
    private static String md5key = "Ms2";

    /**
     * MD5默认秘钥方法加密
     *
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) {
        //加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text + md5key);
        //System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
        return encodeStr;
    }

    /**
     * MD5秘钥方法加密
     *
     * @param text 明文
     * @param key  密钥 null的时候不加秘钥加密
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) {
        if (key == null) {
            key = "";
        }
        //加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text + key);
        //System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
        return encodeStr;
    }

    /**
     * MD5秘钥校验
     *
     * @param text 明文
     * @param key  密钥 null的时候不加秘钥加密
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) {
        if (key == null) {
            key = "";
        }
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }

    /**
     * MD5默认秘钥校验
     *
     * @param text 明文
     * @param md5  密文
     * @return
     */
    public static boolean verify(String text, String md5) {
        //根据传入的密钥进行验证
        String md5Text = md5(text, md5key);
        if (md5Text.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }
}
