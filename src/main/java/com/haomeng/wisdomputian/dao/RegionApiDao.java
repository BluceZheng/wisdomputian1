package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Region;


public interface RegionApiDao {

    //获取最近区域信息
    public Region SearchNearlyLoad(Region region);

}
