package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.ReturnMessage;

public interface CustomerApiDao {

    //微信小程序 获取openid下的用户
    public Customer WChatSearchCustomerInfo(String openid);

    //微信小程序注册用户 openid
    public int WChatRegisterCustomer(Customer customer);

    //小程序更新用户信息
    public int wChatUpdateCustomer(Customer customer);

    //小程序更新用户手机号
    public int WChatUpdateCustomerPhone(Customer customer);

}
