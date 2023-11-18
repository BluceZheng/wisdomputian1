/**
 * e-weaver
 * <p>
 * Copyright (c) 2011 Shanghai Weaver Software Co. Ltd.
 */
package com.haomeng.wisdomputian.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 码比对合并完成-刘海峰 2020-04-08 数字常用工具方法类
 */
public final class NumberHelper {

    /**
     * 将输入的整数转成指定长度的字符串,不足的用0填充
     *
     * @param iIn     需要填充0的整数
     *
     * @param iLength 转换后的字符串的长度
     * @return 用0填充过的指定长度的字符串
     */
    public static String add0(int iIn, int iLength) {
        long lv = (long) Math.pow(10, iLength);
        if (lv < iIn)
            return String.valueOf(iIn);
        return String.valueOf(lv + iIn).substring(1);
    }

    /**
     * 字符串转换为整数，转换异常返回-1。
     *
     * @param strValue 字符串
     * @return 整数
     */
    public static int string2Int(String strValue) {
        return string2Int(strValue, -1);
    }

    /**
     * 字符串转换为整数，可设置转换异常默认值。
     *
     * @param strValue 字符串
     * @param defValue 转换异常默认值
     * @return 整数
     */
    public static int string2Int(String strValue, int defValue) {
        try {
            return Integer.parseInt(strValue);
        } catch (Exception ex) {
            return defValue;
        }
    }

    /**
     * 对象转换为整数，可设置转换异常默认值。
     *
     * @param obj      对象
     * @param defValue 转换异常默认值
     * @return 整数
     */
    public static int string2Int(Object obj, int defValue) {
        if (obj == null) {
            return defValue;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception ex) {
            return defValue;
        }
    }

    /**
     * 判断一个字符串（如11.0）是否为整数，是则返回整数，否则返回-1
     *
     * @param strValue 字符串
     * @return
     */
    public static int string2Int2(String strValue) {
        try {
            if (StringHelper.isEmpty(strValue)) {
                return 0;
            }
            Double dvalue = Double.valueOf(strValue);
            int ivalue = dvalue.intValue();
            if (dvalue == ivalue) {
                return ivalue;
            }
        } catch (Exception ex) {
            return -1;
        }
        return -1;
    }

    /**
     * 判断一个字符串（11.0）是否为整数，是则返回整数，否则返回值
     *
     * @param strValue
     * @return
     */
    public static int string2Int2(String strValue, int defValue) {
        try {
            if (strValue == null) {
                return 0;
            }
            Double dvalue = Double.valueOf(strValue);
            int ivalue = dvalue.intValue();
            if (dvalue == ivalue) {
                return ivalue;
            }
        } catch (Exception ex) {
            return defValue;
        }

        return defValue;
    }

    /**
     * 对象转换为Integer对象，可设置转换异常返回值。
     *
     * @param strValue 对象
     * @param defValue 转换异常返回值
     * @return 转换后Integer对象
     */
    public static Integer getIntegerValue(Object strValue, int defValue) {
        try {
            return Integer.valueOf(StringHelper.null2String(strValue));
        } catch (Exception ex) {
            return new Integer(defValue);
        }
    }

    /**
     * 对象转换为Integer对象，转换异常返回0。
     *
     * @param strValue 对象
     * @return 转换后Integer对象
     */
    public static Integer getIntegerValue(Object strValue) {
        return getIntegerValue(strValue, 0);
    }

    /**
     * 字符串转换为float对象，转换异常返回-1。
     *
     * @param strValue 字符串
     * @return 转换后的float对象
     */
    public static float string2Float(String strValue) {
        return string2Float(strValue, -1);
    }

    /**
     * 字符串转换为float对象，可设置转换异常返回值。
     *
     * @param strValue 字符串
     * @param defValue 转换异常返回值
     * @return 转换后的float对象
     */
    public static float string2Float(String strValue, float defValue) {
        try {
            return Float.parseFloat(strValue);
        } catch (Exception ex) {
            return defValue;
        }
    }

    /**
     *
     * @param strValue
     * @return
     */
    public static double string2Double(String strValue) {
        return string2Double(strValue, -1);
    }

    public static double string2DoubleZero(String strValue) {
        return string2Double(strValue, 0);
    }

    /**
     * 格式化金额每三位加逗号。
     *
     * @param money double类型数字
     * @return
     */
    public static String moneyAddComma(double money) {
        return new java.text.DecimalFormat("#,##0.00").format(money);
    }

    /**
     * 格式化金额每三位加逗号。
     *
     * @param money String类型
     * @return
     */
    public static String moneyAddComma(String money) {
        double tempmoney = string2Double(money, 0);
        return new java.text.DecimalFormat("#,##0.00").format(tempmoney);
    }

    /**
     * 格式化金额每三位加逗号。
     *
     * @param money Object类型
     * @return
     */
    public static String moneyAddComma(Object money) {
        double tempmoney = string2Double(money, 0);
        return new java.text.DecimalFormat("#,##0.00").format(tempmoney);
    }

    /**
     * 大数据格式化金额每三位加逗号。
     *
     * @param money String类型
     * @return
     */
    public static String moneyAddComma(String money, int length) {
        return moneyAddCommaSub(money, length);
    }

    public static String moneyAddCommaSub(String money, int length) {
        String formatmoney = "";
        int index = money.indexOf(".");
        if (index > 0 && money.length() - 1 > index) {
            String f = "";
            String l = "";
            f = money.substring(0, index);// 整数
            l = "0" + money.substring(index);// 小数
            double tmp = string2Double(l, 0);
            String strtmp = moneyFormat(tmp, length);
            String lstr = strtmp.substring(0, strtmp.indexOf("."));
            long flong = Long.parseLong(f) + Long.parseLong(lstr);
            String fmoney = new java.text.DecimalFormat("#,###").format(flong);
            formatmoney = fmoney + strtmp.substring(strtmp.indexOf("."));
        } else {
            long flong = Long.parseLong(money);
            return new java.text.DecimalFormat("#,###").format(flong);
        }

        return formatmoney;
    }

    public static String moneyFormat(double money, int length) {
        if (length < 1)
            length = 2;
        double tempmoney = string2Double(money, 0);
        String p = "#,##0.";
        p += StringHelper.fillString("", length, '0');
        return new java.text.DecimalFormat(p).format(tempmoney);
    }

    /**
     * 字符串转换为double类型，可设置转换异常默认值。
     *
     * @param strValue 字符串
     * @param defValue 转换异常默认值
     * @return 转换后的double类型
     */
    public static double string2Double(String strValue, double defValue) {
        try {
            return Double.parseDouble(strValue);
        } catch (Exception ex) {
            return defValue;
        }
    }

    /**
     * 对象转换为double类型，可设置转换异常默认值。
     *
     * @param obj      对象
     * @param defValue 转换异常默认值
     * @return 转换后的double类型
     */
    public static double string2Double(Object obj, double defValue) {
        if (obj == null) {
            return defValue;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception ex) {
            return defValue;
        }
    }

    /**
     * 获取随机数。
     *
     * @param min 最小值
     * @param max 最大值
     * @return 生成的随机数
     */
    public static int getRandomInt(int min, int max) {

        Random random = new Random();
        return Math.abs(random.nextInt()) % (max - min) + min;
    }

    /**
     * 浮点数四舍五入为整型。
     *
     * @param fValue 浮点数
     * @return 四舍五入后的整数
     */
    public static int float2int(float fValue) {
        int intValue = (int) fValue;
        float tFloat = fValue - intValue;
        intValue = (int) (tFloat + fValue);
        return intValue;
    }

    /**
     *
     * @param num
     * @return
     */
    public static double fixDouble(double num) {
        return fixDouble(num, 2);
    }

    /**
     *
     * @param num
     * @param len
     * @return
     */
    public static double fixDouble(double num, int len) {
        if (len < 0)
            len = 2;
        double n = Math.pow(10, len);
        return (int) Math.floor(num * n) / n;
    }

    /**
     * t
     *
     * @param num
     * @param len
     * @return
     */
    public static double fixDoubleCeil(double num, int len) {
        if (len < 0)
            len = 2;
        double n = Math.pow(10, len);
        return (int) Math.ceil(num * n) / n;
    }

    /**
     * 四舍五入保留两位数
     *
     * @param num
     * @return
     */
    public static String round2Number(String num) {
        if (StringHelper.isEmpty(num)) {
            return "";
        }
        BigDecimal bigdecimal = new BigDecimal(num);
        return bigdecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入，求浮点数 lidachun
     *
     * @param num 输入值
     * @param len 保留小数点后几位
     * @return
     */
    public static double roundDouble(double num, int len) {
        BigDecimal bg = new BigDecimal(num);
        if (len < 0) {
            len = 0;
        }
        double f1 = bg.setScale(len, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 四舍五入保留两位数
     *
     * @param num
     * @return
     */
    public static double round2Number(double num) {
        BigDecimal bigdecimal = new BigDecimal(num);
        return bigdecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
