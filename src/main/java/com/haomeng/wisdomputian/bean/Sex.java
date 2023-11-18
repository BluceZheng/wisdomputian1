package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 性别
 * 1.男
 * 2.女
 * 0.未设置
 */
@Data
public class Sex {
    private String describe;//描述
    private int value;//值
    private String endescribe;//英文描述
}
