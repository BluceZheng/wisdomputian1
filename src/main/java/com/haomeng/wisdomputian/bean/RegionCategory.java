package com.haomeng.wisdomputian.bean;

import lombok.Data;

//区域 可显示 分类
@Data
public class RegionCategory {
    private String id;//编号
    private String regionid;//区域关联编号
    private String categoryid;//分类关联编号
    private String sort;//排序
    private int isdelete;//是否逻辑删除

}
