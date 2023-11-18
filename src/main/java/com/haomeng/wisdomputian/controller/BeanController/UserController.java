package com.haomeng.wisdomputian.controller.BeanController;

import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.mapper.CustomerMapper;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
public class UserController {
    private Logger log = Logger.getLogger(this.getClass());
    private ReturnMessage returnMessage;
    @Resource
    private CustomerMapper userMapper;


    //用户编辑
    @RequestMapping("/user/edituser")
    public String editUserPage(Model model, String userid) {
        model.addAttribute("userid", userid);
        return "pc/user/edituser";
    }

    @RequestMapping("/user/add/adduser")
    public String addUser() {
        return "pc/user/adduser";
    }


}
