package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.RegionCategory;
import com.haomeng.wisdomputian.dao.RegionCategoryDao;
import com.haomeng.wisdomputian.mapper.RegionCategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegionCategoryDaoImp implements RegionCategoryDao {

    @Resource
    private RegionCategoryMapper regionCategoryMapper;


    //添加区域分类信息
    @Override
    public int AddRegionCategory(RegionCategory regionCategory) {
        int result = regionCategoryMapper.AddRegionCategory(regionCategory);
        return result;
    }

    //删除区域中的所有分类
    @Override
    public int DeleteRegionCategoryByRegionid(RegionCategory regionCategory) {
        int result = regionCategoryMapper.DeleteRegionCategoryByRegionid(regionCategory);
        return result;
    }

}
