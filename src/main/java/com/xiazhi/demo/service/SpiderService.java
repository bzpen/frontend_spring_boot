package com.xiazhi.demo.service;

import com.xiazhi.demo.model.Spider;

import java.util.List;

public interface SpiderService {

//    查询种类
    List<Spider> getSpiderList();
//    查询种类id
    Spider getSpiderId(String spiderName);
}
