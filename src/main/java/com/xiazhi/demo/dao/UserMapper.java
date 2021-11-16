package com.xiazhi.demo.dao;


import com.xiazhi.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
*  Mapper注解由Mybatis提供  所以自动装配时会报错，但不影响运行
*  Repository 注解由spring boot 提供，在自动装配时不会报错
 */
@Mapper
@Repository
public interface UserMapper {

//    使用账号登录查询
    User getUser_UserName(User user);

//    使用邮箱登录查询
    User getUser_UserEmail(User user);

//    用户注册
    boolean insertUser(User user);

//    更改用户最近登录时间
    int updateUserTime(User user);

//    更改头像
    int updateUserPhoto(User user);


}
