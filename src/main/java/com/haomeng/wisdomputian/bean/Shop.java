package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class Shop {
    private String id;
    private String shopname;//店铺名称
    private String lat;//纬度
    private String lon;//经度
    private String backgroundimage;//背景图
    private String heardimage;//头像
    private String phone;//联系方式
    private String distance;//距离
    private  String  province;//省
    private String city;//市
    private String country;//区
    private String address;//详细地址
    private String ctime;//创建时间
}
