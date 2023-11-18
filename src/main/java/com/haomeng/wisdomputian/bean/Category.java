package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class Category {

    private String id;//编号
    private String name;//名称
    private String sort;//排序
    private String pictures;//图片
    private int isdeleted;//是否删除
    private String ctime;//新建时间
    private String checked;//是否选中
    private String jumpremark;//跳转标记

}
