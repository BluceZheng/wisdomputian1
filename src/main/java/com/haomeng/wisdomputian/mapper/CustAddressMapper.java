package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.CustAddress;
import com.haomeng.wisdomputian.bean.PageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CustAddressMapper {

    //获取用户地址信息
    public List<CustAddress> SearchCustAddressList(PageParam pageParam);

    //逻辑删除用户地址信息
    public int LogicDeleteCustAddress(CustAddress custAddress);

    //获取全部数量
    public long ObtainUnDeleteSum();

}
