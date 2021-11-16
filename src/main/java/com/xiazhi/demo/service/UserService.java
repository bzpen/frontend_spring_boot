package com.xiazhi.demo.service;

import com.xiazhi.demo.model.User;

public interface UserService {

    //    使用账号登录查询
    String getUser_UserName(User user);

    //    使用邮箱登录查询
    User getUser_UserEmail(User user);

    //    注册新用户
    String insertUser(User user);

    //  检查用户名/邮箱是否重复
    String checkUserName(User user);

    // 获取用户数据
    User getUser(User user);

    //    更新用户最近登录时间
    public int updateUserTime(User user);

//    用户头像更改
    boolean updateUserPhoto(User user);
}
