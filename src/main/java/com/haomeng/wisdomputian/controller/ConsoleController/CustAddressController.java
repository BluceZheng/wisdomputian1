package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.CustAddress;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.config.errorCode;
import com.haomeng.wisdomputian.daoimp.CustAddressDaoImp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/custaddress/")
@Controller
public class CustAddressController {
    private Logger log = Logger.getLogger(this.getClass());
    //返回数据
    private ReturnMessage returnMessage;
    private PageParam pageParam;

    @Resource
    private CustAddressDaoImp custAddressDaoImp;


    //获取用户地址信息
    @PostMapping("searchcustaddresslist")
    @ResponseBody
    public ReturnMessage SearchCustAddressList(int limit, int page, String id) {
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
        //设置id
        pageParam.setObjid(id);
        //获取数据量
        long dataSum = custAddressDaoImp.ObtainUnDeleteSum();
        //获取用户地址信息
        List<CustAddress> custAddressList = custAddressDaoImp.SearchCustAddressList(pageParam);
        returnMessage = new ReturnMessage();
        if (custAddressList.isEmpty()) {
            returnMessage.setErroCode(errorCode.ERROR_CODE_NULL);
            returnMessage.setErrorMessage(errorCode.ERROR_MESSAGE_NULL);
        } else {
            log.info("selectAllCustomer-----------SearchCustAddressList-----获取用户地址信息===========");
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("查询成功");
            returnMessage.setBackData(custAddressList);
            returnMessage.setDataSum(dataSum);
            returnMessage.setDataType("List<CustAddress>");
        }
        return returnMessage;
    }

    //物理删除客户地址信息
    @PostMapping("logicdeletecustaddress")
    @ResponseBody
    public ReturnMessage logicdeletecustaddress(CustAddress custAddress) {
        returnMessage = new ReturnMessage();
        int result = custAddressDaoImp.LogicDeleteCustAddress(custAddress);
        if (result > 0) {//删除成功
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("删除成功");
        } else {//删除失败
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("删除失败");
        }
        return returnMessage;
    }


}
