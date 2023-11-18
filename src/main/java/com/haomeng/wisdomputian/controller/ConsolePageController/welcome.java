package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class welcome {

    @GetMapping("welcome")
    public String welcomemain(){
        return "pc/welcome";
    }
}
