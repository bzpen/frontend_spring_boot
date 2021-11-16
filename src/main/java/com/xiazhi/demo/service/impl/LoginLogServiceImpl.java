package com.xiazhi.demo.service.impl;

import com.xiazhi.demo.dao.LoginLogMapper;
import com.xiazhi.demo.model.LoginLog;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.service.LoginLogService;
import com.xiazhi.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;


    @Autowired
    UserService userService;
    /**
     * 登录日志插入
     *
     * @param user
     * @return LoginLog
     */
    @Override
    public int userLogin(User user) {
        try{
            LoginLog loginLog=new LoginLog();
            loginLog.setUserId(userService.getUser(user).getUserId());
            int result=loginLogMapper.userLogin(loginLog);
            return result;
        }catch (Exception e){
            log.info("数据库操作异常" + e.toString());
            return -1;
        }
    }

}
