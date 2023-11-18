package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//订单页面
@Controller
public class CustAddressPageController {
    private Logger log = Logger.getLogger(this.getClass());

    //获取用户地址信息
    @RequestMapping("/custaddresspage")
    public String CustAddressPage(Model model, String custaddressid) {
        log.info("【CustAddressPageController】-----------获取用户地址信息-----CustAddress管理");
        model.addAttribute("custaddressid", custaddressid);
        return "pc/custaddress/custaddress";
    }

}
