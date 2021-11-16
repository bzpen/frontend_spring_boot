package com.xiazhi.demo.dao;

import com.xiazhi.demo.model.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginLogMapper {

//    登录日志添加
    public int userLogin(LoginLog loginLog);


}
