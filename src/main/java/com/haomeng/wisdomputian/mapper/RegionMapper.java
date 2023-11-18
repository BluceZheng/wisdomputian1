package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Region;
import com.haomeng.wisdomputian.bean.PageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper {
    //获取所有的区域图
    public List<Region> SelectAllRegion(PageParam pageParam);

    //获取所有的已删除的区域图
    public List<Region> SearchAllDeletedRegion(PageParam pageParam);

    //获取分类数量
    public long ObtainSum();

    //逻辑删除区域信息
    public int LogicDeleteRegion(Region region);

    //查找区域信息byid
    public Region SearchSimpleRegion(String regionid);

    //更新区域信息
    public int UpdateRegion(Region region);

    //还原逻辑删除分类信息
    public int LogicDeleteRegionReject(Region region);

    //正式删除分类信息
    public int RealDeleteRegion(Region region);

    //添加区域信息
    public int AddRegion(Region region);

    //获取最近区域信息
    public Region SearchNearlyLoad(Region region);
}
