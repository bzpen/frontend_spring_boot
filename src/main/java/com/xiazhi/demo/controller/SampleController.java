package com.xiazhi.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiazhi.demo.model.Sample;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.model.extension.Sample_Spider;
import com.xiazhi.demo.service.SampleService;
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
import java.util.List;
import java.util.Random;

@Controller
@ResponseBody
@Slf4j
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    UserService userService;

    private HashMap<String,Object> hs=new HashMap<>();

    /**
     * 获取样本序列
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    public String getSampleList() throws JsonProcessingException {
        log.info("请求数据");
        String state="false";
        List<Sample_Spider> samples=sampleService.getSampleList();
        hs.clear();
        if(samples!=null){
            log.info("ok");
            state="true";
            hs.put("samples",samples);
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    /**
     * 获取用户个人上传数据
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/user_list")
    public String getSampleList_User(HttpServletRequest request) throws JsonProcessingException {
        hs.clear();
        String state="false";
        String token = request.getHeader("token");
        User user = TokenUtil.tokenUser(token);
        List<Sample_Spider> samples=sampleService.getSampleList_User(user);
        if(samples != null){
            state="true";
            hs.put("samples",samples);
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    /**
     * 插入新的样本序列
     * @param sample
     * @param user
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/insertSample")
    public String insertSample(Sample sample,User user) throws JsonProcessingException{
        hs.clear();
        String state="false";
        sample.setUserId(userService.getUser(user).getUserId());
        int result=sampleService.insertSample(sample);
        if(result==1){
            state="true";
            hs.put("sampleNumber",sample.getSampleNumber());
        }else if(result==-1){
            hs.put("msg","样本编号可能重复，请检查后重试！");
        }else{
            hs.put("msg","经纬度计算错误，请检查地址并重试！");
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    /**
     * 主页搜索请求处理
     * @param str
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/selectResult")
    public String getSelectResult(String str) throws JsonProcessingException{
        System.out.println(str);
        hs.clear();
        String state="false";
        List<Sample_Spider> samples=sampleService.getSelectResult(str);
        if(samples != null){
            state="true";
            hs.put("samples",samples);
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    /**
     * 坐标数据获取
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/getCoordinate")
    public String getCoordinate() throws JsonProcessingException{
        log.info("请求地图数据");
        String state="false";
        List<Sample_Spider> samples=sampleService.getSampleList();
        hs.clear();
        if(samples != null){
            samples = sampleService.getCoordinate(samples);
            state="true";
            hs.put("samples",samples);
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }
}
