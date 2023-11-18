package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.ManagerUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerUserMapper {

    /**
     * 登陆校验查找用户
     *
     * @param name
     * @return
     */
    @Select("select * from manageruser where username = #{name}")
    public ManagerUser LoginValidSelect(String name);


}
