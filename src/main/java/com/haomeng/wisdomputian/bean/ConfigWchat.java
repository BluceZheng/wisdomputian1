package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class ConfigWchat {
    private int id;
    private String bindid;
    private String type;
    private String linkurl;
    private String supplier;
    private String validsign;
}
