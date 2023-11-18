package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegionPageController {

    private Logger log = Logger.getLogger(this.getClass());

    //获取区域信息
    @RequestMapping("/regions")
    public String regionPage() {
        log.info("【RegionPageController】=============获取区域信息=============");
        return "pc/region/region";
    }

    //添加区域信息
    @RequestMapping("/region/add/addregion")
    public String addRegion() {
        log.info("【RegionPageController】=============添加区域信息=============");
        return "pc/region/addregion";
    }

    //修改区域信息
    @RequestMapping("/region/edit/editregion")
    public String editRegionpage(Model model, String regionid) {
        model.addAttribute("regionid", regionid);
        log.info("【RegionPageController】=============修改区域信息=============");
        return "pc/region/editregion";
    }

    //已删除的区域信息
    @RequestMapping("/region/deletedregion")
    public String deletedRegion() {
        log.info("RegionPageController=============已删除的区域信息======================");
        return "pc/region/deletedregion";
    }

    //获取区域分类信息
    @RequestMapping("/region/edit/editregioncategory")
    public String editRegionCategory(Model model, String regionid) {
        model.addAttribute("regionid", regionid);
        log.info("【editRegionCategory】=============获取区域分类信息=============");
        return "pc/region/editregioncategory";
    }

}
