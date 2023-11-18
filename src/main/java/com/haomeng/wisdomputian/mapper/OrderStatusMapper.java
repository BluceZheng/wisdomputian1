package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.OrderStatus;
import com.haomeng.wisdomputian.bean.OrderType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderStatusMapper {

    @Select("select *  from orderstatus")
    public List<OrderStatus> selectOrderStatusList();

}
