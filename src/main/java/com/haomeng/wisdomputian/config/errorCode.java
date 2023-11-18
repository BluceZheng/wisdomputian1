package com.haomeng.wisdomputian.config;

public class errorCode {
    //成功状态信息
    public static int SUCCESS_STATUS = 200;
    public static String SUCCESS_MESSAGE = "操作成功";

    //登陆状态信息
    public static int LOGIN_FAIL_STATUS = 300;
    //失败状态信息
    public static int FAIL_STATUS = 500;
    public static String FAIL_MESSAGE = "执行失败";

    //失败状态信息
    public static int ERROR_CODE_NULL = 206;
    public static String ERROR_MESSAGE_NULL = "获取的数据为空";

    //登陆状态信息
    public static int ERROR_CODE_LOGIN = 400;
    public static String ERROR_MESSAGE_LOGIN_NULL = "登陆失败，账号密码不能为空";
    public static String ERROR_MESSAGE_LOGIN_PWDWRONG = "登陆失败，密码不匹配";

    //频繁操作
    public static int ERROR_CODE_FREQUENTLY = 201;
    public static String ERROR_MESSAGE_FREQUENTLY = "频繁操作";

    //banner获取消息
    public static String ERROR_MESSAGE_BANNER_NULL = "没有获取到数据";


    //注册失败
    public static int ERROR_CODE_REGISTER = 100;
    public static String ERROR_MESSAGE_REGISTER = "注册失败";


    public static int ERROR_ILLEGAL = 202;//非法操作
    public static int ERROR_INPUT_NULL = 101;//输入为空
    public static int ERROR_THROW_WRONG = 300;//抛出错误
    public static int ERROR_OTHER = 001;


}
