package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BannerPageController {

    private Logger log = Logger.getLogger(this.getClass());

    //获取广告信息
    @RequestMapping("/banners")
    public String bannersPage() {
        log.info("【BannerPageController】-----------获取广告信息-----banner管理");
        return "pc/banner/banner";
    }

    //添加广告信息
    @RequestMapping("/banner/add/addbanner")
    public String addbanner() {
        log.info("【BannerPageController】-----------添加广告信息-----添加banner");
        return "pc/banner/addbanner";
    }

    //修改广告信息
    @RequestMapping("/banner/edit/editbanner")
    public String editbannerpage(Model model, String bannerid) {
        model.addAttribute("bannerid", bannerid);
        log.info("【BannerPageController】-----------修改广告信息-----");
        return "pc/banner/editbanner";
    }

    //已删除的广告信息
    @RequestMapping("/banner/deletedbanner")
    public String deletedBanner() {
        log.info("BannerPageController=============已删除的广告信息======================");
        return "pc/banner/deletedbanner";
    }

}
