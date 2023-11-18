package com.haomeng.wisdomputian.bean;

import lombok.Data;

/**
 * 订单状态 add by zjh 20211110
 * 1.客户下单
 * 2.等待业务员联系
 * 3.业务已经联系待业务员确认
 * 4.业务处理中
 * 5.服务完成客户评价
 * 6.双方互评完成订单
 * 7.完成订单
 * <p>
 * 0.订单异常
 */
@Data
public class OrderStatus {
    //订单状态码
    private String statusno;
    //订单描述
    private String statusdescript;
    //备注
    private String remark;
}
