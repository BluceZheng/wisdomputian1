package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class Region {
    private String id;//编号
    private String lon;//经度
    private String lat;//纬度
    private String addrdetails;//地址全称
    private String abbreviation;//地址简称
    private String shopid;//--商铺编号--
    private String province;//省
    private String city;//市
    private String area;//区
    private String address;//详细地址
    private String ctime;//--创建时间--
    private int isdelete;//--是否删除--
}
