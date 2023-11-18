package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.Region;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.config.errorCode;
import com.haomeng.wisdomputian.daoimp.RegionDaoImp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/regions")
public class RegionConsoleController {
    private Logger log = Logger.getLogger(this.getClass());

    private ReturnMessage returnMessage;

    private PageParam pageParam;

    @Resource
    private RegionDaoImp regionDaoImp;

    //获取所有的区域信息
    @GetMapping("/selectallbanner")
    @ResponseBody
    public ReturnMessage selectAllRegion(int limit, int page) {
        //入参数据
        pageParam = new PageParam();
        int pageStart = (page - 1) * limit;
        //开始页数
        pageParam.setPagestart(pageStart);
        int pageEnd = page * limit - 1;
        //结束页数
        pageParam.setPageend(pageEnd);
        //设置页面限制数
        pageParam.setLimit(limit);
        //设置第几页数
        pageParam.setPagenum(page);
        //获取数据量
        long dataSum = regionDaoImp.ObtainSum();
        List<Region> regionList = regionDaoImp.SelectAllRegion(pageParam);

        returnMessage = new ReturnMessage();
        if (regionList.isEmpty()) {
            returnMessage.setErroCode(errorCode.ERROR_CODE_NULL);
            returnMessage.setErrorMessage(errorCode.ERROR_MESSAGE_NULL);
        } else {
            log.info("【RegionConsoleController】-----------selectAllRegion=========获取所有的区域信息");
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("查询成功");
            returnMessage.setBackData(regionList);
            returnMessage.setDataSum(dataSum);
            returnMessage.setDataType("List<Banner>");
        }
        return returnMessage;
    }

    //逻辑删除区域信息
    @PostMapping("/logicdeleteregion")
    @ResponseBody
    public ReturnMessage logicDeleteRegion(Region region) {
        log.info("逻辑删除区域信息============logicDeleteBanner========");
        returnMessage = new ReturnMessage();
        //添加区域
        int result = regionDaoImp.LogicDeleteRegion(region);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("移除成功");
            log.info("逻辑删除成功============logicDeleteBanner========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("移除失败");
            log.info("逻辑删除失败============logicDeleteBanner========");
        }
        return returnMessage;
    }

    //获取单条区域信息
    @PostMapping("/searchsimpleregion")
    @ResponseBody
    public ReturnMessage SearchSimpleRegion(String regionid) {
        log.info("获取单条区域信息============SearchSimpleRegion========");
        returnMessage = new ReturnMessage();
        //添加分类
        Region region = regionDaoImp.SearchSimpleRegion(regionid);
        if (region != null) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setBackData(region);
            returnMessage.setDataType("Region");
            returnMessage.setErrorMessage("查找成功");
            log.info("查找成功============SearchSimpleRegion========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("查找失败");
            log.info("查找失败============SearchSimpleRegion========");
        }
        return returnMessage;
    }

    //更新区域信息
    @PostMapping("/updateregion")
    @ResponseBody
    public ReturnMessage updateRegion(Region region) {
        log.info("修改方法============updateRegion========");
        returnMessage = new ReturnMessage();
        //更新区域信息
        int result = regionDaoImp.UpdateRegion(region);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("修改成功");
            log.info("修改成功============updateRegion========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("修改失败");
            log.info("修改失败============updateRegion========");
        }
        return returnMessage;
    }

    //控制台获取区域已删除所有数据
    @GetMapping("/searchalldeletedregion")
    @ResponseBody
    public ReturnMessage SearchAllDeletedRegion(int limit, int page) {
        //入参数据
        pageParam = new PageParam();

        int pageStart = (page - 1) * limit;
        //开始页数
        pageParam.setPagestart(pageStart);
        int pageEnd = page * limit - 1;
        //结束页数
        pageParam.setPageend(pageEnd);
        //设置页面限制数
        pageParam.setLimit(limit);
        //设置第几页数
        pageParam.setPagenum(page);
        //获取数据量
        long dataSum = regionDaoImp.ObtainSum();
        //查找数据
        List<Region> regionList = regionDaoImp.SearchAllDeletedRegion(pageParam);

        returnMessage = new ReturnMessage();
        returnMessage.setErroCode(200);
        returnMessage.setDataSum(dataSum);
        returnMessage.setErrorMessage("查询成功");
        returnMessage.setDataType("List<Region>");
        returnMessage.setBackData(regionList);
        return returnMessage;
    }

    //还原区域删除信息
    @PostMapping("/logicdeleteregionreject")
    @ResponseBody
    public ReturnMessage logicDeleteRegionReject(Region region) {
        log.info("还原区域删除信息============logicDeleteRegionReject========");
        returnMessage = new ReturnMessage();
        //还原区域删除信息
        int result = regionDaoImp.LogicDeleteRegionReject(region);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("还原成功");
            log.info("还原区域删除信息============logicDeleteRegionReject========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("还原失败");
            log.info("还原区域删除信息============logicDeleteRegionReject========");
        }
        return returnMessage;
    }

    //删除区域
    @PostMapping("/realdeleteregion")
    @ResponseBody
    public ReturnMessage realDeleteRegion(Region region) {
        log.info("逻辑删除区域============realDeleteRegion========");
        returnMessage = new ReturnMessage();
        //逻辑删除区域
        int result = regionDaoImp.RealDeleteRegion(region);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("删除成功");
            log.info("删除成功============realDeleteRegion========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("删除失败");
            log.info("删除失败============realDeleteRegion========");
        }
        return returnMessage;
    }

    //添加区域
    @PostMapping("/addregion")
    @ResponseBody
    public ReturnMessage addRegion(Region region) {
        log.info("添加方法============addRegion========");
        returnMessage = new ReturnMessage();
        //添加区域
        int result = regionDaoImp.AddRegion(region);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("添加成功");
            log.info("添加成功============addRegion========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("添加失败");
            log.info("添加失败============addRegion========");
        }
        return returnMessage;
    }

}
