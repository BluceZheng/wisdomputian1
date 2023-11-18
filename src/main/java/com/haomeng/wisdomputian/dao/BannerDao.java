package com.haomeng.wisdomputian.dao;

import com.haomeng.wisdomputian.bean.Banner;
import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;

import java.util.List;

public interface BannerDao {

    //获取所有的广告图
    public List<Banner> selectAllBanner(PageParam pageParam);

    //获取所有的已删除的广告图
    public List<Banner> SearchAllDeletedBanner(PageParam pageParam);

    //获取数量
    public long ObtainSum();

    //逻辑删除分类
    public int LogicDeleteBanner(Banner banner);

    //查找单条广告数据信息
    public Banner SearchSimpleBanner(String bannerid);

    //更新广告信息
    public int UpdateBanner(Banner banner);

    //还原逻辑删除广告
    public int LogicDeleteBannerReject(Banner banner);

    //正式删除广告
    public int RealDeleteBanner(Banner banner);

    //添加广告信息
    public int AddBanner(Banner banner);

}
