package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 订单类型
 * 1.车检
 * 2.上门换轮胎
 * 3.上门收废品
 * 4.车辆保养
 */
@Data
public class OrderType {
    private String id;//编号
    private String describe;//描述
    private String remark;//备注
}
