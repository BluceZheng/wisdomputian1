package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 广告图片
 */
@Data
public class Banner {
    private String id;//编号
    private String cuid;//创建人
    private String bindid;//关联绑定id
    private String picture;//图片
    private String linkurl;//链接
    private String title;//标题
    private int status;//状态
    private int sort;//排序
    private String create_time;//创建时间
    private int isdelete;//是否删除

}
