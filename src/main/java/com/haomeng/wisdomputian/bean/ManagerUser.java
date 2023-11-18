package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 管理用户
 */
@Data
public class ManagerUser {
    private String id;//编号
    private String bindid;//绑定编号
    private String username;//用户名
    private String nickname;//昵称
    private String phone;//手机号
    private String password;//密码
    private String birthday;//生日
    private String idcard;//身份证
    private int age;//年龄
    private int sex;//性别
    private String head_portrait;//头像
    private int status;//账号状态
}
