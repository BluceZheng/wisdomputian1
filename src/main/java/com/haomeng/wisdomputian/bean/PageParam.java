package com.haomeng.wisdomputian.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PageParam {
    private int limit;//页面条数限制
    private int pagenum;//
    private int pagestart;
    private int pageend;
    private int id;
    private String objid;
    private int plantgrassid;
    private int userid;
    private int state;
    private List<Object> objectList;
    private long DataSum;
    private String lon;//经度
    private String lat;//纬度
    private String isdelete;//删除状态
    private String sysflag;//系統标识
    private String isactive;//是否激活

}
