package com.haomeng.wisdomputian.bean;

/**
 * 图片上传
 */
public class UploadData {
    private String src;//图片路径
    private String title;//图片名称 可选

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
