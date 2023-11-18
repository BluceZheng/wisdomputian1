package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.Category;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.RegionCategory;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.daoimp.CategoryDaoImp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/categorys")
public class CategoryConsoleController {

    private Logger log = Logger.getLogger(this.getClass());

    private ReturnMessage returnMessage;

    private PageParam pageParam;
    @Resource
    private CategoryDaoImp categoryDaoImp;

    //控制台获取分类未删除所有数据
    @GetMapping("/getcategorys")
    @ResponseBody
    public ReturnMessage SearchAllCategory(int limit, int page) {
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
        long dataSum = categoryDaoImp.ObtainSum();
        //查找数据
        List<Category> categoryList = categoryDaoImp.SearchAllCategory(pageParam);

        returnMessage = new ReturnMessage();
        returnMessage.setErroCode(200);
        returnMessage.setDataSum(dataSum);
        returnMessage.setErrorMessage("查询成功");
        returnMessage.setDataType("List<Category>");
        returnMessage.setBackData(categoryList);
        return returnMessage;
    }

    //控制台获取分类已删除所有数据
    @GetMapping("/searchalldeletedcategory")
    @ResponseBody
    public ReturnMessage SearchAllDeletedCategory(int limit, int page) {
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
        long dataSum = categoryDaoImp.ObtainSum();
        //查找数据
        List<Category> categoryList = categoryDaoImp.SearchAllDeletedCategory(pageParam);

        returnMessage = new ReturnMessage();
        returnMessage.setErroCode(200);
        returnMessage.setDataSum(dataSum);
        returnMessage.setErrorMessage("查询成功");
        returnMessage.setDataType("List<Category>");
        returnMessage.setBackData(categoryList);
        return returnMessage;
    }

    //添加分类
    @PostMapping("/addcategory")
    @ResponseBody
    public ReturnMessage addCategory(Category category) {
        log.info("添加方法============addCategory========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = categoryDaoImp.AddCategory(category);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("添加成功");
            log.info("添加成功============addCategory========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("添加失败");
            log.info("添加失败============addCategory========");
        }
        return returnMessage;
    }

    //修改分类
    @PostMapping("/editcategory")
    @ResponseBody
    public ReturnMessage editCategory(Category category) {
        log.info("添加方法============editCategory========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = categoryDaoImp.UpdateCategory(category);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("修改成功");
            log.info("修改成功============editCategory========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("修改失败");
            log.info("修改失败============editCategory========");
        }
        return returnMessage;
    }

    //逻辑删除分类
    @PostMapping("/logicdeletecategory")
    @ResponseBody
    public ReturnMessage logicDeleteCategory(Category category) {
        log.info("逻辑删除分类============logicDeleteCategory========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = categoryDaoImp.LogicDeleteCategory(category);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("移除成功");
            log.info("逻辑删除成功============logicDeleteCategory========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("移除失败");
            log.info("逻辑删除失败============logicDeleteCategory========");
        }
        return returnMessage;
    }

    //还原分类删除信息
    @PostMapping("/logicdeletecategoryreject")
    @ResponseBody
    public ReturnMessage logicDeleteCategoryReject(Category category) {
        log.info("逻辑删还原分类============logicDeleteCategoryReject========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = categoryDaoImp.LogicDeleteCategoryReject(category);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("还原成功");
            log.info("逻辑删还原分类============logicDeleteCategoryReject========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("还原失败");
            log.info("逻辑删还原分类============logicDeleteCategoryReject========");
        }
        return returnMessage;
    }

    //删除分类
    @PostMapping("/realdeletecategory")
    @ResponseBody
    public ReturnMessage realDeleteCategory(Category category) {
        log.info("逻辑删除分类============logicDeleteCategory========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = categoryDaoImp.RealDeleteCategory(category);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("删除成功");
            log.info("逻辑删除成功============logicDeleteCategory========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("删除失败");
            log.info("逻辑删除失败============logicDeleteCategory========");
        }
        return returnMessage;
    }

    //获取单条分类信息
    @PostMapping("/searchsimplecategory")
    @ResponseBody
    public ReturnMessage SearchSimpleCategory(String categoryid) {
        log.info("逻辑删除分类============SearchSimpleCategory========");
        returnMessage = new ReturnMessage();
        //添加分类
        Category category = categoryDaoImp.SearchSimpleCategory(categoryid);
        if (category != null) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setBackData(category);
            returnMessage.setDataType("Category");
            returnMessage.setErrorMessage("查找成功");
            log.info("查找成功============SearchSimpleCategory========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("查找失败");
            log.info("查找失败============SearchSimpleCategory========");
        }
        return returnMessage;
    }

    //获取指定区域可显示的区域分类信息
    @PostMapping("/searchregioncategory")
    @ResponseBody
    public ReturnMessage SearchRegionCategory(RegionCategory regionCategory) {
        returnMessage = new ReturnMessage();
        List<Category> categoryList = categoryDaoImp.SearchRegionCategory(regionCategory);
        if (categoryList.isEmpty()) {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("没有查询到数据");
        } else {
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("查询到数据");
            returnMessage.setBackData(categoryList);
            returnMessage.setDataType("List<Category>");
        }
        return returnMessage;
    }

}
