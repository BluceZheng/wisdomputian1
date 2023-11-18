package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class ReturnUploadMessage {
    private int code;//0表示成功，其它失败
    private String msg;//提示信息 //一般上传失败后返回
    private UploadData data;//返回的上传文件的数据
}
