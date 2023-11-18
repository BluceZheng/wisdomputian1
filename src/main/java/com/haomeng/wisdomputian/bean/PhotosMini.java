package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class PhotosMini {

    private String id;
    private String linkid;//关联的id
    private String path;//图片路径
    private String name;//图片名称
    private String size;//文件大小
    private String isdelete;//是否删除 1 删除 0 否删除

}
