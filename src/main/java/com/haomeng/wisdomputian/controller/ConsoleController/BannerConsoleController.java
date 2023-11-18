package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.Banner;
import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.controller.ConsolePageController.BannerPageController;
import com.haomeng.wisdomputian.daoimp.BannerDaoImp;
import com.haomeng.wisdomputian.mapper.BannerMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.haomeng.wisdomputian.config.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/banners")
public class BannerConsoleController {
    private Logger log = Logger.getLogger(this.getClass());

    private ReturnMessage returnMessage;

    private PageParam pageParam;

    @Resource
    private BannerDaoImp bannerDaoImp;

    //获取所有的广告图
    @GetMapping("/selectallbanner")
    @ResponseBody
    public ReturnMessage selectAllBanner(int limit, int page) {
        //入参数据
        pageParam = new PageParam();
        int pageStart = (page - 1) * limit;
        //开始页数
        pageParam.setPagestart(pageStart);
        int pageEnd = page * limit - 1;
        //结束页数
        pageParam.setPageend(pageEnd);
        //设置页面限制数
        pageParam.setLimit(limit);
        //设置第几页数
        pageParam.setPagenum(page);
        //获取数据量
        long dataSum = bannerDaoImp.ObtainSum();
        List<Banner> bannerList = bannerDaoImp.selectAllBanner(pageParam);

        returnMessage = new ReturnMessage();
        if (bannerList.isEmpty()) {
            returnMessage.setErroCode(errorCode.ERROR_CODE_NULL);
            returnMessage.setErrorMessage(errorCode.ERROR_MESSAGE_NULL);
        } else {
            log.info("【BannerConsoleController】-----------bannerList-----控制台获取banner列表");
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("查询成功");
            returnMessage.setBackData(bannerList);
            returnMessage.setDataSum(dataSum);
            returnMessage.setDataType("List<Banner>");
        }
        return returnMessage;
    }

    //逻辑删除广告
    @PostMapping("/logicdeletebanner")
    @ResponseBody
    public ReturnMessage logicDeleteBanner(Banner banner) {
        log.info("逻辑删除分类============logicDeleteBanner========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = bannerDaoImp.LogicDeleteBanner(banner);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("移除成功");
            log.info("逻辑删除成功============logicDeleteBanner========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("移除失败");
            log.info("逻辑删除失败============logicDeleteBanner========");
        }
        return returnMessage;
    }

    //获取单条广告信息
    @PostMapping("/searchsimplebanner")
    @ResponseBody
    public ReturnMessage SearchSimpleBanner(String bannerid) {
        log.info("逻辑删除分类============SearchSimpleBanner========");
        returnMessage = new ReturnMessage();
        //添加分类
        Banner banner = bannerDaoImp.SearchSimpleBanner(bannerid);
        if (banner != null) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setBackData(banner);
            returnMessage.setDataType("Banner");
            returnMessage.setErrorMessage("查找成功");
            log.info("查找成功============SearchSimpleBanner========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("查找失败");
            log.info("查找失败============SearchSimpleBanner========");
        }
        return returnMessage;
    }

    //更新广告信息
    @PostMapping("/updateBanner")
    @ResponseBody
    public ReturnMessage updateBanner(Banner banner) {
        log.info("修改方法============updateBanner========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = bannerDaoImp.UpdateBanner(banner);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("修改成功");
            log.info("修改成功============updateBanner========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("修改失败");
            log.info("修改失败============updateBanner========");
        }
        return returnMessage;
    }

    //控制台获取广告已删除所有数据
    @GetMapping("/searchalldeletedbanner")
    @ResponseBody
    public ReturnMessage SearchAllDeletedBanner(int limit, int page) {
        //入参数据
        pageParam = new PageParam();

        int pageStart = (page - 1) * limit;
        //开始页数
        pageParam.setPagestart(pageStart);
        int pageEnd = page * limit - 1;
        //结束页数
        pageParam.setPageend(pageEnd);
        //设置页面限制数
        pageParam.setLimit(limit);
        //设置第几页数
        pageParam.setPagenum(page);
        //获取数据量
        long dataSum = bannerDaoImp.ObtainSum();
        //查找数据
        List<Banner> bannerList = bannerDaoImp.SearchAllDeletedBanner(pageParam);

        returnMessage = new ReturnMessage();
        returnMessage.setErroCode(200);
        returnMessage.setDataSum(dataSum);
        returnMessage.setErrorMessage("查询成功");
        returnMessage.setDataType("List<Banner>");
        returnMessage.setBackData(bannerList);
        return returnMessage;
    }

    //还原广告删除信息
    @PostMapping("/logicdeletebannerreject")
    @ResponseBody
    public ReturnMessage logicDeleteBannerReject(Banner banner) {
        log.info("逻辑删还原分类============logicDeleteBannerReject========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = bannerDaoImp.LogicDeleteBannerReject(banner);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("还原成功");
            log.info("逻辑删还原分类============logicDeleteBannerReject========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("还原失败");
            log.info("逻辑删还原分类============logicDeleteBannerReject========");
        }
        return returnMessage;
    }

    //删除广告
    @PostMapping("/realdeletebanner")
    @ResponseBody
    public ReturnMessage realDeleteBanner(Banner banner) {
        log.info("逻辑删除分类============realDeleteBanner========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = bannerDaoImp.RealDeleteBanner(banner);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("删除成功");
            log.info("删除成功============realDeleteBanner========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("删除失败");
            log.info("删除失败============realDeleteBanner========");
        }
        return returnMessage;
    }

    //添加广告
    @PostMapping("/addbanner")
    @ResponseBody
    public ReturnMessage addBanner(Banner banner) {
        log.info("添加方法============addBanner========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = bannerDaoImp.AddBanner(banner);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("添加成功");
            log.info("添加成功============addCategory========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("添加失败");
            log.info("添加失败============addCategory========");
        }
        return returnMessage;
    }

}
