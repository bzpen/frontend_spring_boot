package com.xiazhi.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiazhi.demo.model.Spider;
import com.xiazhi.demo.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;


/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
@Controller
@ResponseBody
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    SpiderService spiderService;

    private HashMap<String,Object> hs=new HashMap<>();


    @PostMapping("/getSpiders")
    public String getSpiders() throws JsonProcessingException{
        hs.clear();
        String state="false";
        List<Spider> spiders=spiderService.getSpiderList();
        if(spiders!=null){
            state="true";
            hs.put("spider",spiders);
        }
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);

    }
}
