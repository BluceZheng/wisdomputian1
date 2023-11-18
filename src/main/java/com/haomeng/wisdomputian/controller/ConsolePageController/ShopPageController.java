package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopPageController {
    private Logger log = Logger.getLogger(ShopPageController.class);


    //shop管理
    @RequestMapping("/shops")
    public String shopsPage() {
        log.info("【ShopPageController】-----------shopsPage-----shop管理");
        return "pc/shop/shop";
    }

    //添加shop
    @RequestMapping("/shop/add/addshop")
    public String addshop() {
        log.info("【ShopPageController】-----------addshop-----添加shop");
        return "pc/shop/addshop";
    }

    //修改shop
    @RequestMapping("/shop/edit/editshop")
    public String editshoppage(Model model, @Param("shopid") int shopid) {
        model.addAttribute("shopid", shopid);
        log.info("【ShopPageController】-----------editshoppage-----修改shop");
        return "pc/shop/editshop";
    }

}
