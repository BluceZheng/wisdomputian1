package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class CustAddress {

    private String id;
    private String linkid;//关联id
    private String linkname;//链接人姓名
    private String name;//姓名
    private String phone;//手机号
    private String isdefault;//是否默认
    private String province;//省
    private String city;//市
    private String area;//区域
    private String addressdetails;//详细地址
    private int isdelete; //是否删除

}
