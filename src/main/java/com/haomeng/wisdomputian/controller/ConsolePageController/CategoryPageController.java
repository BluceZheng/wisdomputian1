package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryPageController {
    private Logger log = Logger.getLogger(this.getClass());

    //获取分类信息
    @GetMapping("/categorys")
    public String Category() {
        log.info("CategoryPageController===========获取分类信息============");
        return "pc/category/category";
    }

    //添加分类信息
    @GetMapping("/addcategory")
    public String AddCategory() {
        log.info("CategoryPageController==============添加分类信息==============");
        return "pc/category/addcategory";
    }

    //修改分类信息
    @GetMapping("/editcategory")
    public String EditCategory(Model model, String categoryid) {
        log.info("CategoryPageController===============修改分类信息==================");
        model.addAttribute("categoryid", categoryid);
        return "pc/category/editcategory";
    }

    //已删除分类信息
    @GetMapping("/deletedcategory")
    public String DeletedCategory(Model model, String categoryid) {
        log.info("CategoryPageController==============已删除分类信息===================");
        model.addAttribute("categoryid", categoryid);
        return "pc/category/deletedcategory";
    }

}
