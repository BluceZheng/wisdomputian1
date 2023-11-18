package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 获取订单信息
     *
     * @param order
     * @return
     */
    @Select("<script> " +
            "select odr.orderno," +
            "odr.status as state," +
            "(select os.statusdescript from orderstatus os where os.statusno =odr.status ) as status," +
            "odr.province," +
            "odr.city," +
            "odr.country," +
            "odr.area," +
            "(select ot.describe from ordertype ot where ot.id= odr.type) as type," +
            "odr.phone," +
            "odr.filepdf," +
            "odr.wchat_openid," +
            "odr.customername," +
            "odr.creattime " +
            " from `order` odr " +
            "where odr.orderno like #{orderno} " +
            "and odr.phone like #{phone} " +
            "<if test=\"status != -1\"> " +
            "and odr.status = #{status}" +
            "</if>" +
            "<if test=\"type != -1\"> " +
            "and odr.type = #{type}" +
            "</if> " +
            "</script>")
    public List<Order> selectAllOrder(Order order);

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @Insert(" insert into `order` (id,bindid,orderno, status, province, city, country, area, type, phone, customername,creattime) " +
            "        values (#{id},#{bindid}, #{orderno},1, #{province}, #{city}, #{country}, #{area}, #{type}, #{phone}, " +
            "                #{customername},#{creattime})")
    public int generalOrder(Order order);

}
