package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Banner;
import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.dao.BannerDao;
import com.haomeng.wisdomputian.mapper.BannerMapper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerDaoImp implements BannerDao {

    @Resource
    private BannerMapper bannerMapper;

    //获取所有的广告图
    @Override
    public List<Banner> selectAllBanner(PageParam pageParam) {
        List<Banner> bannerList = bannerMapper.selectAllBanner(pageParam);
        return bannerList;
    }

    //获取所有的已删除的广告图
    @Override
    public List<Banner> SearchAllDeletedBanner(PageParam pageParam) {
        List<Banner> bannerList = bannerMapper.SearchAllDeletedBanner(pageParam);
        return bannerList;
    }

    //获取数量
    @Override
    public long ObtainSum() {
        long result = bannerMapper.ObtainSum();
        return result;
    }

    //逻辑删除分类
    @Override
    public int LogicDeleteBanner(Banner banner) {
        int result = bannerMapper.LogicDeleteBanner(banner);
        return result;
    }

    //查找单条广告数据信息
    @Override
    public Banner SearchSimpleBanner(String bannerid) {
        Banner banner = bannerMapper.SearchSimpleBanner(bannerid);
        return banner;
    }

    //更新广告信息
    @Override
    public int UpdateBanner(Banner banner) {
        int result = bannerMapper.UpdateBanner(banner);
        return result;
    }

    //还原逻辑删除分类
    @Override
    public int LogicDeleteBannerReject(Banner banner) {
        int result = bannerMapper.LogicDeleteBannerReject(banner);
        return result;
    }

    //正式删除分类
    @Override
    public int RealDeleteBanner(Banner banner) {
        int result = bannerMapper.RealDeleteBanner(banner);
        return result;
    }

    //添加广告信息
    @Override
    public int AddBanner(Banner banner) {
        String id = UUIDUtil.getNoLineUUID();
        banner.setId(id);
        int result = bannerMapper.AddBanner(banner);
        return result;
    }


}
