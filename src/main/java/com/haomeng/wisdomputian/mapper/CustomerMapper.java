package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.PageParam;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CustomerMapper {

    //获取所有的用户
    public List<Customer> selectAllCustomer(PageParam pageParam);

    //获取所有的已删除的用户
    public List<Customer> SearchAllDeletedCustomer(PageParam pageParam);

    //获取用户数量
    public long ObtainSum();

    //逻辑删除用户信息
    public int LogicDeleteCustomer(Customer Customer);

    //查找用户信息byid
    public Customer SearchSimpleCustomer(String Customerid);

    //更新用户信息
    public int UpdateCustomer(Customer Customer);

    //还原逻辑删除用户信息
    public int LogicDeleteCustomerReject(Customer Customer);

    //正式删除用户信息
    public int RealDeleteCustomer(Customer Customer);

    //添加用户信息
    public int AddCustomer(Customer Customer);

    //重置密码
    public int resetPWD(Customer Customer);
}
