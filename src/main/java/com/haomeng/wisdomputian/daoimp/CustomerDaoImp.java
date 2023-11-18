package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.dao.CustomerDao;
import com.haomeng.wisdomputian.mapper.CustomerMapper;
import com.haomeng.wisdomputian.util.PasswordMD5;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerDaoImp implements CustomerDao {

    @Resource
    private CustomerMapper customerMapper;

    //获取所有的用户图
    @Override
    public List<Customer> SearchAllCustomer(PageParam pageParam) {
        List<Customer> CustomerList = customerMapper.selectAllCustomer(pageParam);
        return CustomerList;
    }

    //获取所有的已删除的用户图
    @Override
    public List<Customer> SearchAllDeletedCustomer(PageParam pageParam) {
        List<Customer> CustomerList = customerMapper.SearchAllDeletedCustomer(pageParam);
        return CustomerList;
    }

    //获取未删除用户数量
    @Override
    public long ObtainSum() {
        long result = customerMapper.ObtainSum();
        return result;
    }

    @Override
    public List<Customer> SearchCustomer(Customer Customer) {
        return null;
    }

    //逻辑删除用户
    @Override
    public int LogicDeleteCustomer(Customer Customer) {
        int result = customerMapper.LogicDeleteCustomer(Customer);
        return result;
    }

    //查找单条用户数据信息
    @Override
    public Customer SearchSimpleCustomer(String Customerid) {
        Customer Customer = customerMapper.SearchSimpleCustomer(Customerid);
        return Customer;
    }

    //更新用户信息
    @Override
    public int UpdateCustomer(Customer Customer) {
        int result = customerMapper.UpdateCustomer(Customer);
        return result;
    }

    //还原逻辑删除用户
    @Override
    public int LogicDeleteCustomerReject(Customer Customer) {
        int result = customerMapper.LogicDeleteCustomerReject(Customer);
        return result;
    }

    //正式删除用户
    @Override
    public int RealDeleteCustomer(Customer Customer) {
        int result = customerMapper.RealDeleteCustomer(Customer);
        return result;
    }

    //添加用户信息
    @Override
    public int AddCustomer(Customer Customer) {
        String id = UUIDUtil.getNoLineUUID();
        Customer.setId(id);
        int result = customerMapper.AddCustomer(Customer);
        return result;
    }

    //重置密码
    @Override
    public int resetCustomerPWD(Customer Customer) {
        //密码加密
        String pwd = Customer.getPassword();
        Customer.setPassword(PasswordMD5.md5(pwd));
        //更新密码
        int result = customerMapper.resetPWD(Customer);
        return result;
    }

}
