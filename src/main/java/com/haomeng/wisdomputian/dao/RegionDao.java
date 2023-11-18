package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Region;
import com.haomeng.wisdomputian.bean.PageParam;

import java.util.List;

public interface RegionDao {

    //获取所有的区域
    public List<Region> SelectAllRegion(PageParam pageParam);

    //获取所有的已删除的区域
    public List<Region> SearchAllDeletedRegion(PageParam pageParam);

    //获取数量
    public long ObtainSum();

    //逻辑删除区域
    public int LogicDeleteRegion(Region region);

    //查找单条区域数据信息
    public Region SearchSimpleRegion(String regionid);

    //更新区域信息
    public int UpdateRegion(Region region);

    //还原逻辑删除区域
    public int LogicDeleteRegionReject(Region region);

    //正式删除区域
    public int RealDeleteRegion(Region region);

    //添加区域信息
    public int AddRegion(Region region);

}
