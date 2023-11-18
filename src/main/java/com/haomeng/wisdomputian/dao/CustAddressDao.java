package com.haomeng.wisdomputian.dao;


import com.haomeng.wisdomputian.bean.CustAddress;
import com.haomeng.wisdomputian.bean.PageParam;

import java.util.List;

public interface CustAddressDao {

    //获取用户地址信息
    public List<CustAddress> SearchCustAddressList(PageParam pageParam);


    //删除用户地址
    public int LogicDeleteCustAddress(CustAddress custAddress);

    //获取全部数量
    public long ObtainUnDeleteSum();

}
