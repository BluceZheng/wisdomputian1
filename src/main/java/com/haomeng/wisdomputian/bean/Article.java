package com.haomeng.wisdomputian.bean;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private String id;//编号
    private String requestid;//关系字段
    private String title;//标题
    private String content;//内容
    private int isdelete;//是否删除 1 是 0 否
    private String photos;//图片路径
    private String address;//地址信息
    private String persion;//联系人
    private String phone;//联系人手机
    private String money;//金额
    private int examine;//审核字段：0.待审核；1.通过；2.拒绝
    private String reason;//理由
    private String lon;//经度
    private String lat;//纬度
    private String titleimg;//标题图片路径
    private String distance;//距离
    private String typename;//分类描述
    private String typeid;//分类id
    private String crttime;//创建时间
    private List<Photos> photosList;//关联的图片
}
