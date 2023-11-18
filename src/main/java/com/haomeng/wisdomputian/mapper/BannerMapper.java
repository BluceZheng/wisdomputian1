package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Banner;
import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerMapper {

    //获取所有的广告图
    public List<Banner> selectAllBanner(PageParam pageParam);

    //获取所有的已删除的广告图
    public List<Banner> SearchAllDeletedBanner(PageParam pageParam);

    //获取分类数量
    public long ObtainSum();

    //逻辑删除广告信息
    public int LogicDeleteBanner(Banner banner);

    //查找广告信息byid
    public Banner SearchSimpleBanner(String bannerid);

    //更新广告信息
    public int UpdateBanner(Banner banner);

    //还原逻辑删除分类信息
    public int LogicDeleteBannerReject(Banner banner);

    //正式删除分类信息
    public int RealDeleteBanner(Banner banner);

    //添加广告信息
    public int AddBanner(Banner banner);
}
