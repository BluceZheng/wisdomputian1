package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.LongitudeAndLatitude;
import com.haomeng.wisdomputian.bean.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    //获取最近距离的店铺
    List<Shop> searchstoredistance(LongitudeAndLatitude longitudeAndLatitude);

    //新建店铺
    int creatShop(Shop shop);

    //更新店铺信息
    int updateShopInfo(Shop shop);

    //获取所有的店铺信息
    public List<Shop> searchstors();


}
