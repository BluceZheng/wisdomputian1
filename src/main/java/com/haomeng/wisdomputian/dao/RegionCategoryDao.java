package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.RegionCategory;

import java.util.List;

public interface RegionCategoryDao {

    //添加区域分类信息
    public int AddRegionCategory(RegionCategory regionCategory);

    //删除区域中的所有分类
    public int DeleteRegionCategoryByRegionid(RegionCategory regionCategory);

}
