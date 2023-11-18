package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.PageParam;

import java.util.List;

public interface CustomerDao {

    //查找未删除分类全部信息
    public List<Customer> SearchAllCustomer(PageParam pageParam);

    //查找已删除分类全部信息
    public List<Customer> SearchAllDeletedCustomer(PageParam pageParam);

    //获取数量
    public long ObtainSum();

    //查找分类信息
    public List<Customer> SearchCustomer(Customer Customer);

    //添加分类信息
    public int AddCustomer(Customer Customer);

    //更新分类信息
    public int UpdateCustomer(Customer Customer);

    //逻辑删除分类
    public int LogicDeleteCustomer(Customer Customer);

    //还原逻辑删除分类
    public int LogicDeleteCustomerReject(Customer Customer);

    //正式删除分类
    public int RealDeleteCustomer(Customer Customer);

    //查找单条分类数据信息
    public Customer SearchSimpleCustomer(String Customerid);

    //重置密码
    public int resetCustomerPWD(Customer Customer);


}
