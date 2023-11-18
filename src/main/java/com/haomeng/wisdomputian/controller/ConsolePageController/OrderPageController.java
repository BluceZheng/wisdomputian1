package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单页面
 * author 郑界华
 */
@Controller
public class OrderPageController {
    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping("order")
    public String order() {
        return "pc/order/order";
    }



}
