package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.*;
import com.haomeng.wisdomputian.mapper.OrderMapper;
import com.haomeng.wisdomputian.mapper.OrderStatusMapper;
import com.haomeng.wisdomputian.mapper.OrderTypeMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderConsoleController {
    private Logger log = Logger.getLogger(this.getClass());
    private ReturnMessage returnMessage;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderStatusMapper orderTypeStatusMapper;
    @Resource
    private OrderTypeMapper orderTypeMapper;


    /**
     * 订单状态更新
     *
     * @param orderid    订单id
     * @param orderstate 订单状态
     * @return
     */
    @PostMapping("/order/updateorderstate")
    @ResponseBody
    public ReturnMessage updateOrderState(int orderid, int orderstate) {
        returnMessage = new ReturnMessage();
        PageParam pageParam = new PageParam();
        pageParam.setState(orderstate);
        pageParam.setId(orderid);
        return returnMessage;
    }

    /**
     * 获取订单数据
     *
     * @param orderno
     * @param orderstate
     * @param phone
     * @return
     */
    @PostMapping("/order/obtainorder")
    @ResponseBody
    public ReturnMessage obtainOrder(String orderno, String orderstate, String phone, String type) {
        returnMessage = new ReturnMessage();
        Order order = new Order();
        if (orderno == null) {
            orderno = "%";
        } else {
            orderno = "%" + orderno + "%";
        }
        if (phone == null) {
            phone = "%";
        } else {
            phone = "%" + phone + "%";
        }
        order.setOrderno(orderno);
        order.setStatus(orderstate);
        order.setPhone(phone);
        order.setType(type);
        List<Order> orderList = orderMapper.selectAllOrder(order);
        if (orderList.isEmpty()) {
            returnMessage.setErroCode(500);
            returnMessage.setErrorMessage("还没有下过订单");
        } else {
            returnMessage.setErroCode(200);
            returnMessage.setErrorMessage("获取订单成功");
            returnMessage.setBackData(orderList);
            returnMessage.setDataType("List<Order>");
        }
        return returnMessage;
    }

    /**
     * 订单状态修改为处理状态
     *
     * @param id
     * @param type
     * @return
     */
    public ReturnMessage updateOrderHandleStatus(int id, int type) {
        returnMessage = new ReturnMessage();


        return returnMessage;
    }

    /**
     * 订单状态修改为处理完成
     *
     * @param id
     * @param type
     * @return
     */
    public ReturnMessage updateOrderCompletedStatus(int id, int type) {
        returnMessage = new ReturnMessage();


        return returnMessage;
    }


    /**
     * 获取订单类型、订单状态
     *
     * @return
     */
    @PostMapping("/order/typestatus")
    @ResponseBody
    public ReturnMessage obtainOrderTypeStatus() {
        returnMessage = new ReturnMessage();
        //获取订单状态
        List<OrderStatus> orderStatusList = orderTypeStatusMapper.selectOrderStatusList();
        //获取订单类型
        List<OrderType> orderTypeList = orderTypeMapper.selectOrderTypeList();
        Map<String, Object> backData = new HashMap<>();
        backData.put("orderStatus", orderStatusList);
        backData.put("orderType", orderTypeList);
        returnMessage.setBackData(backData);
        returnMessage.setErrorMessage("获取成功");
        returnMessage.setErroCode(200);
        log.info("订单类型-订单状态：数据获取成功");
        return returnMessage;
    }

}
