package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.dao.CustomerApiDao;
import com.haomeng.wisdomputian.mapper.CustomerApiMapper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerApiDaoImp implements CustomerApiDao {
    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private CustomerApiMapper customerApiMapper;

    //微信小程序 获取openid下的用户
    @Override
    public Customer WChatSearchCustomerInfo(String openid) {
        return customerApiMapper.WChatSearchCustomerInfo(openid);
    }

    //微信小程序注册用户 openid
    @Override
    public int WChatRegisterCustomer(Customer customer) {
        String id = customer.getId();
        if ("".equals(id) || id == null || id == "") {
            id = UUIDUtil.getNoLineUUID();
            customer.setId(id);
        }
        int result = customerApiMapper.WChatRegisterCustomer(customer);
        return result;
    }

    //小程序更新用户信息
    @Override
    public int wChatUpdateCustomer(Customer customer) {
        log.info("================小程序更新用户信息=============");
        int result = customerApiMapper.WChatUpdateCustomer(customer);
        return result;
    }

    //小程序更新用户手机号
    @Override
    public int WChatUpdateCustomerPhone(Customer customer) {
        log.info("================小程序更新用户手机号=============");
        int result = customerApiMapper.WChatUpdateCustomerPhone(customer);
        return result;
    }

}
