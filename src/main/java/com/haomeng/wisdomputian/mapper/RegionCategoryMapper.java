package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.RegionCategory;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RegionCategoryMapper {

    //添加区域分类信息
    public int AddRegionCategory(RegionCategory regionCategory);

    //删除区域中的所有分类
    public int DeleteRegionCategoryByRegionid(RegionCategory regionCategory);

}
