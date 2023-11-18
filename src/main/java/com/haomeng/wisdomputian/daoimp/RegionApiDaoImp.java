package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Region;
import com.haomeng.wisdomputian.dao.RegionApiDao;
import com.haomeng.wisdomputian.mapper.RegionApiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegionApiDaoImp implements RegionApiDao {

    @Resource
    private RegionApiMapper regionApiMapper;

    //获取最近区域信息
    @Override
    public Region SearchNearlyLoad(Region region) {
        return regionApiMapper.SearchNearlyLoad(region);
    }

}
