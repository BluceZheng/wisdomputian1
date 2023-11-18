package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionApiMapper {

    //获取最近区域信息
    public Region SearchNearlyLoad(Region region);
}
