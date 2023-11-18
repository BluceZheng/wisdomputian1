package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class ReturnMessage {
    private int erroCode;
    private String errorMessage;
    private String DataType;
    private Object backData;
    private long DataSum;
}
