package com.xiazhi.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.service.LoginLogService;
import com.xiazhi.demo.service.UserService;
import com.xiazhi.demo.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static com.xiazhi.demo.utils.TokenUtil.sign;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginLogService loginLogService;

    private HashMap<String,Object> hs=new HashMap<>();

    /**
     * 用户名密码登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String userLogin(User user) throws JsonProcessingException {
        log.info(user.getUserName()!=null?user.getUserName():user.getUserEmail());
        String result=userService.getUser_UserName(user);
        hs.clear();
        if(result == "1"){
            boolean res = loginLogService.userLogin(user)>0 && userService.updateUserTime(user)>0;
            System.out.println(res);
            if(res){
                user = userService.getUser(user);
                hs.put("token",sign(user));
            }else{
                result="-1";
            }
        }
        hs.put("state",result);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }
    /**
     * 邮箱登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/loginEmail")
    @ResponseBody
    public String userEmailLogin(User user) throws JsonProcessingException{
        log.info(user.getUserEmail());
        User result=userService.getUser_UserEmail(user);
        hs.clear();
        if(result != null){
            boolean res = loginLogService.userLogin(user)>0 && userService.updateUserTime(user)>0;
            if(res){
                hs.put("state",'1');
                hs.put("token",sign(result));
            }else{
                hs.put("state","-1");
            }
        }else{
            hs.put("state","-1");
        }
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    /**
     * 新用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public String userRegister(User user){
        log.info(user.getUserName());
        String result=userService.insertUser(user);
        return result;
    }

    /**
     * 邮箱/用户名重复验证
     *
     * @param user
     * @return
     */
    @GetMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(User user){
        String result=userService.checkUserName(user);
        return result;
    }


    /**
     * token验证/更新
     *
     * @param token
     * @return
     */
    @PostMapping("/checkToken")
    @ResponseBody
    public String checkToken(String token) throws JsonProcessingException{
        String state="false";
        hs.clear();
        if(token != null){
            boolean result = TokenUtil.verify(token);
            System.out.println(result);
            if(result){
                state="true";
                User user = TokenUtil.tokenUser(token);
                boolean res = loginLogService.userLogin(user)>0 && userService.updateUserTime(user)>0;
                hs.put("token",sign(user));
            }
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }
    /**
     * 用户信息获取
     *
     * @param request
     * @return
     */
    @PostMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request) throws JsonProcessingException{
        hs.clear();
        String state="false";
        String token = request.getHeader("token");
        if(token != null){
            User user = TokenUtil.tokenUser(token);
            user=userService.getUser(user);
            if(user!=null){
                hs.put("user",user);
                state="true";
            }
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

}
