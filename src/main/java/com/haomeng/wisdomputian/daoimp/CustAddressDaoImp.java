package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.CustAddress;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.dao.CustAddressDao;
import com.haomeng.wisdomputian.mapper.CustAddressMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustAddressDaoImp implements CustAddressDao {

    @Resource
    private CustAddressMapper custAddressMapper;


    //获取用户地址信息
    @Override
    public List<CustAddress> SearchCustAddressList(PageParam pageParam) {
        return custAddressMapper.SearchCustAddressList(pageParam);
    }

    //逻辑删除用户地址信息
    @Override
    public int LogicDeleteCustAddress(CustAddress custAddress) {
        return custAddressMapper.LogicDeleteCustAddress(custAddress);
    }

    //获取全部数量
    @Override
    public long ObtainUnDeleteSum() {
        return custAddressMapper.ObtainUnDeleteSum();
    }


}
