package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.Customer;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.config.errorCode;
import com.haomeng.wisdomputian.daoimp.CustomerDaoImp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerConsoleController {

    private Logger log = Logger.getLogger(this.getClass());

    private ReturnMessage returnMessage;

    private PageParam pageParam;

    @Resource
    private CustomerDaoImp customerDaoImp;

    //获取所有的用户图
    @GetMapping("/selectallcustomer")
    @ResponseBody
    public ReturnMessage SearchAllCustomer(int limit, int page) {
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
        long dataSum = customerDaoImp.ObtainSum();
        List<Customer> customerList = customerDaoImp.SearchAllCustomer(pageParam);

        returnMessage = new ReturnMessage();
        if (customerList.isEmpty()) {
            returnMessage.setErroCode(errorCode.ERROR_CODE_NULL);
            returnMessage.setErrorMessage(errorCode.ERROR_MESSAGE_NULL);
        } else {
            log.info("selectAllCustomer-----------SearchAllCustomer-----获取所有的用户图===========");
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("查询成功");
            returnMessage.setBackData(customerList);
            returnMessage.setDataSum(dataSum);
            returnMessage.setDataType("List<customer>");
        }
        return returnMessage;
    }

    //逻辑删除用户
    @PostMapping("/logicdeletecustomer")
    @ResponseBody
    public ReturnMessage logicDeleteCustomer(Customer customer) {
        log.info("逻辑删除分类============logicDeletecustomer========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = customerDaoImp.LogicDeleteCustomer(customer);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("移除成功");
            log.info("逻辑删除成功============logicDeletecustomer========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("移除失败");
            log.info("逻辑删除失败============logicDeletecustomer========");
        }
        return returnMessage;
    }

    //获取单条用户信息
    @PostMapping("/searchsimplecustomer")
    @ResponseBody
    public ReturnMessage SearchSimpleCustomer(String customerid) {
        log.info("逻辑删除分类============SearchSimplecustomer========");
        returnMessage = new ReturnMessage();
        //添加分类
        Customer customer = customerDaoImp.SearchSimpleCustomer(customerid);
        if (customer != null) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setBackData(customer);
            returnMessage.setDataType("customer");
            returnMessage.setErrorMessage("查找成功");
            log.info("查找成功============SearchSimplecustomer========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("查找失败");
            log.info("查找失败============SearchSimplecustomer========");
        }
        return returnMessage;
    }

    //更新用户信息
    @PostMapping("/updatecustomer")
    @ResponseBody
    public ReturnMessage updateCustomer(Customer customer) {
        log.info("修改方法============updatecustomer========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = customerDaoImp.UpdateCustomer(customer);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("修改成功");
            log.info("修改成功============updatecustomer========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("修改失败");
            log.info("修改失败============updatecustomer========");
        }
        return returnMessage;
    }

    //控制台获取用户已删除所有数据
    @GetMapping("/searchalldeletedcustomer")
    @ResponseBody
    public ReturnMessage SearchAllDeletedCustomer(int limit, int page) {
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
        long dataSum = customerDaoImp.ObtainSum();
        //查找数据
        List<Customer> customerList = customerDaoImp.SearchAllDeletedCustomer(pageParam);

        returnMessage = new ReturnMessage();
        returnMessage.setErroCode(200);
        returnMessage.setDataSum(dataSum);
        returnMessage.setErrorMessage("查询成功");
        returnMessage.setDataType("List<customer>");
        returnMessage.setBackData(customerList);
        return returnMessage;
    }

    //还原用户删除信息
    @PostMapping("/logicdeletecustomerreject")
    @ResponseBody
    public ReturnMessage logicDeleteCustomerReject(Customer customer) {
        log.info("逻辑删还原分类============logicDeletecustomerReject========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = customerDaoImp.LogicDeleteCustomerReject(customer);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("还原成功");
            log.info("逻辑删还原分类============logicDeletecustomerReject========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("还原失败");
            log.info("逻辑删还原分类============logicDeletecustomerReject========");
        }
        return returnMessage;
    }

    //删除用户
    @PostMapping("/realdeletecustomer")
    @ResponseBody
    public ReturnMessage realDeleteCustomer(Customer customer) {
        log.info("逻辑删除分类============realDeletecustomer========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = customerDaoImp.RealDeleteCustomer(customer);
        if (result == 1) {//如果成功执行
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("删除成功");
            log.info("删除成功============realDeletecustomer========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("删除失败");
            log.info("删除失败============realDeletecustomer========");
        }
        return returnMessage;
    }

    //添加用户
    @PostMapping("/addcustomer")
    @ResponseBody
    public ReturnMessage addCustomer(Customer customer) {
        log.info("添加方法============addcustomer========");
        returnMessage = new ReturnMessage();
        //添加分类
        int result = customerDaoImp.AddCustomer(customer);
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

    //用户密码重置
    @PostMapping("/resetCustomerpwd")
    @ResponseBody
    public ReturnMessage resetCustomerPWD(Customer customer) {
        log.info("重置密码方法============resetCustomerPWD========");
        returnMessage = new ReturnMessage();
        //设置默认密码
        customer.setPassword("123456");
        //重置密码
        int result = customerDaoImp.resetCustomerPWD(customer);
        if (result == 1) {//如果成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("重置成功");
            log.info("重置成功============resetCustomerPWD========");
        } else {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("重置失败");
            log.info("重置失败============resetCustomerPWD========");
        }
        return returnMessage;
    }

}
