package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.OrderType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderTypeMapper {
    @Select("select *  from ordertype")
    public List<OrderType> selectOrderTypeList();
}
