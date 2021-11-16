package com.xiazhi.demo.dao;

import com.xiazhi.demo.model.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
@Mapper
@Repository
public interface LoginLogMapper {

//    登录日志添加
    public int userLogin(LoginLog loginLog);


}
