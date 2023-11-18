package com.haomeng.wisdomputian.bean;

import lombok.Data;

import java.io.Serializable;

//位置信息返回实体类
@Data
public class ReturnLocation implements Serializable {

    private static final Long serializableValue = 1L;

    //地理位置
    private String formattedAddress;

    //经度
    private String lng;

    //纬度
    private String lat;

    //品级
    private String level;
}
