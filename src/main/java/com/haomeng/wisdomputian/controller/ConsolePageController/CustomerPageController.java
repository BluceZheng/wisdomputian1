package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//订单页面
@Controller
public class CustomerPageController {
    private Logger log = Logger.getLogger(this.getClass());

    //获取用户信息
    @RequestMapping("/customers")
    public String customersPage() {
        log.info("【CustomerPageController】-----------获取用户信息-----customer管理");
        return "pc/customer/customer";
    }

    //添加用户信息
    @RequestMapping("/customers/add/addcustomer")
    public String addCustomer() {
        log.info("【CustomerPageController】-----------添加用户信息-----添加customer");
        return "pc/customer/addcustomer";
    }

    //修改用户信息
    @RequestMapping("/customers/edit/editcustomer")
    public String editCustomerPage(Model model, String customerid) {
        model.addAttribute("customerid", customerid);
        log.info("【CustomerPageController】--------editCustomerPage---修改用户信息-----");
        return "pc/customer/editcustomer";
    }

    //已删除的用户信息
    @RequestMapping("/customers/deletedcustomer")
    public String deletedCustomer() {
        log.info("CustomerPageController=============已删除的用户信息======================");
        return "pc/customer/deletedcustomer";
    }


}
