package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BannerApiMapper {
    /**
     * 查询banner数据 小程序
     *
     * @param status 1:显示 0：隐藏
     * @return
     */
    public List<Banner> selectStatusBanner(int status);

    /**
     * 更新banner
     *
     * @param banner
     * @return
     */
    public int updateBannerById(Banner banner);
}
