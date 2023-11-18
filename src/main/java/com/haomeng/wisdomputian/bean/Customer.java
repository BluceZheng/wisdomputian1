package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 客户编码
 */
@Data
public class Customer {
    private String id;//编号
    private String bindid;//绑定的id
    private String username;//用户名
    private String nickname;//昵称
    private String password;//密码
    private String headportrait;//头像
    private long fans_num;//粉丝
    private long follow_num;//关注
    private long likes_num;//点赞
    private long collect_num;//收藏
    private String remark;//备注
    private String phone;//手机号
    private String wchatuuid;//微信uuid
    private int sex;//性别
    private String birthday;//生日
    private String create_time;//注册时间
    private String wchat_session_key;//微信的sessionkey
    private String wchat_openid;//微信的openid
    private String isdelete;//是否删除
    private String token;//标识
    private String picture;//头像
    private String fans;//粉丝数
    private String tag;//用户标签
}
