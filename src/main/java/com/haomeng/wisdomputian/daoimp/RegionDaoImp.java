package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Region;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.dao.RegionDao;
import com.haomeng.wisdomputian.mapper.RegionMapper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegionDaoImp implements RegionDao {

    @Resource
    private RegionMapper regionMapper;

    //获取所有的区域
    @Override
    public List<Region> SelectAllRegion(PageParam pageParam) {
        List<Region> RegionList = regionMapper.SelectAllRegion(pageParam);
        return RegionList;
    }

    //获取所有的已删除的区域
    @Override
    public List<Region> SearchAllDeletedRegion(PageParam pageParam) {
        List<Region> RegionList = regionMapper.SearchAllDeletedRegion(pageParam);
        return RegionList;
    }

    //获取数量
    @Override
    public long ObtainSum() {
        long result = regionMapper.ObtainSum();
        return result;
    }

    //逻辑删除区域
    @Override
    public int LogicDeleteRegion(Region region) {
        int result = regionMapper.LogicDeleteRegion(region);
        return result;
    }

    //查找单条区域数据信息
    @Override
    public Region SearchSimpleRegion(String regionid) {
        Region Region = regionMapper.SearchSimpleRegion(regionid);
        return Region;
    }

    //更新区域信息
    @Override
    public int UpdateRegion(Region region) {
        int result = regionMapper.UpdateRegion(region);
        return result;
    }

    //还原逻辑删除区域
    @Override
    public int LogicDeleteRegionReject(Region region) {
        int result = regionMapper.LogicDeleteRegionReject(region);
        return result;
    }

    //正式删除区域
    @Override
    public int RealDeleteRegion(Region region) {
        int result = regionMapper.RealDeleteRegion(region);
        return result;
    }

    //添加区域信息
    @Override
    public int AddRegion(Region region) {
        String id = UUIDUtil.getNoLineUUID();
        region.setId(id);
        int result = regionMapper.AddRegion(region);
        return result;
    }

}
