package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 订单
 */
@Data
public class Order {
    private String id;
    private String bindid;
    private String orderno;

    private String status;//订单状态
    private String state;//订单值
    private String province;//省份
    private String city;//市
    private String country;//区
    private String area;//详细
    private String type;//订单类型
    private String phone;
    private String filepdf;
    private String wchat_openid;
    private String customername;
    private String creattime;
}
