package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.RegionCategory;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.daoimp.RegionCategoryDaoImp;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
@RequestMapping("/regioncategorys/")
public class RegionCategoryConsoleController {
    private Logger log = Logger.getLogger(this.getClass());

    private ReturnMessage returnMessage;

    @Resource
    private RegionCategoryDaoImp regionCategoryDaoImp;


    //添加区域分类信息
    @RequestMapping("addregioncategory")
    @ResponseBody
    public ReturnMessage AddRegionCategory(RegionCategory regionCategory) {
        log.info("添加区域分类信息============AddRegionCategory========");
        returnMessage = new ReturnMessage();
        //移除区域分类信息
        regionCategoryDaoImp.DeleteRegionCategoryByRegionid(regionCategory);

        String errmsg = "";
        String categoryidStr = regionCategory.getCategoryid();
        if (categoryidStr != null && !categoryidStr.isEmpty()) {
            String[] categoryids = categoryidStr.split(",");
            for (String categoryid : categoryids) {
                String id = UUIDUtil.getNoLineUUID();
                regionCategory.setId(id);
                regionCategory.setCategoryid(categoryid);
                int result = regionCategoryDaoImp.AddRegionCategory(regionCategory);
                if (result < 1) {
                    errmsg += "," + categoryid;
                }
            }
            errmsg += "以上的分类信息添加失败!";
        } else {
            errmsg = "分类信息添加成功！";
        }
        returnMessage.setErroCode(200);
        returnMessage.setErrorMessage(errmsg);
        return returnMessage;
    }

}
