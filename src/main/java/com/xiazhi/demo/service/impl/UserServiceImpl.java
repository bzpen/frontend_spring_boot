package com.xiazhi.demo.service.impl;

import com.xiazhi.demo.dao.UserMapper;
import com.xiazhi.demo.model.LoginLog;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.service.UserService;
import com.xiazhi.demo.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final long EXPIRE_TIME = 8 * 60 * 60 * 1000;

    @Autowired
    UserMapper userMapper;

    /**
     * 用户名密码登录
     *
     * @param user
     * @return String
     */
    @Override
    public String getUser_UserName(User user) {
        try {
            User resultUser=userMapper.getUser_UserName(user);
            user.setUserPass(MD5Utils.code(user.getUserPass()));
            if(resultUser!=null){
                if(user.getUserPass().equalsIgnoreCase(resultUser.getUserPass())){
                    log.info("账号密码输入正确成功登录！");
                    return "1";
//                账号密码输入正确成功登录！
                }else{
                    log.info("密码输入错误！");
                    return "2";
//                密码输入错误！
                }
            }else{
                log.info("该账号不存在！");
                return "0";
            }
        } catch (Exception e) {
            log.info("数据库操作异常:" + e.toString());
            return "-1";
        }

    }

    /**
     * 邮箱登录
     *
     * @param user
     * @return User
     */
    @Override
    public User getUser_UserEmail(User user) {
        try{
            User resultUser=userMapper.getUser_UserName(user);
            return resultUser;
        }catch (Exception e){
            log.info("数据库操作异常:" + e.toString());
            return null;
        }
    }

    /**
     * 普通新用户注册
     *
     * @param user
     * @return String
     */
    @Override
    public String insertUser(User user) {
        try{
            user.setUserPass(MD5Utils.code(user.getUserPass()));
            boolean resultUser=userMapper.insertUser(user);
            if(resultUser){
                log.info(user.getUserName() + "注册成功！");
                return "1";
            }else{
                log.info(user.getUserName() + "注册失败！");
                return "0";
            }

        }catch (Exception e){
            log.info("数据库操作异常:" + e.toString());
            return "-1";
        }
    }

    /**
     * 用户名/邮箱重复验证
     *
     * @param user
     * @return String
     */
    @Override
    public String checkUserName(User user) {
        if(user.getUserName()!=null){
            log.info(user.getUserName() + "账号正在验证");
        }else{
            log.info(user.getUserEmail() + "邮箱正在验证");
        }
        try{
            User resultUser=userMapper.getUser_UserName(user);
            if(resultUser!=null) {
                return "0";
            }
        }catch (Exception e){
            log.info("数据库操作异常:" + e.toString());
            return "-1";
        }
        return "1";
    }

    /**
     * 用户信息查询
     *
     * @param user
     * @return String
     */
    @Override
    public User getUser(User user) {
        try{
            User resultUser=userMapper.getUser_UserName(user);
            return resultUser;
        }catch (Exception e){
            log.info("数据库操作异常:" + e.toString());
            return null;
        }
    }

    /**
     * 用户最近登录信息更新
     *
     * @param user
     * @return int
     */
    @Override
    public int updateUserTime(User user) {
        try{
            user = userMapper.getUser_UserName(user);
            user.setUpLoginTime(new Date(System.currentTimeMillis()));
            int result = userMapper.updateUserTime(user);
            return result;
        }catch (Exception e){
            log.info(e.toString());
            return -1;
        }
    }

    /**
     * 用户头像更改
     * @param user
     * @return
     */
    @Override
    public boolean updateUserPhoto(User user) {
        try{
            user = userMapper.getUser_UserName(user);
            int result = userMapper.updateUserPhoto(user);
            if(result>0)
                return true;
            return false;
        }catch (Exception e){
            log.info(e.toString());
            return false;
        }
    }
}
