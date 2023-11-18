package com.haomeng.wisdomputian.controller.ConsolePageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class indexpage {

    @GetMapping
    public String login() {
        return "/pc/Login";
    }

    @GetMapping("index")
    public String index() {
        return "/pc/default";
    }
}
