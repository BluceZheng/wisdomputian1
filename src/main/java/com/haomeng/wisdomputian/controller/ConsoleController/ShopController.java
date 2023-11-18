package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.bean.SearchListPageParam;
import com.haomeng.wisdomputian.mapper.ShopMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/shops")
public class ShopController {
    private Logger log = Logger.getLogger(ShopController.class);
    private ReturnMessage returnMessage;
    @Resource
    private ShopMapper shopMapper;

    private SearchListPageParam searchListPageParam;

    @RequestMapping("/shops")
    @ResponseBody
    public ReturnMessage obtainShops(String startnum, String endnum) {
        returnMessage = new ReturnMessage();


        return returnMessage;
    }


}
