package com.xiazhi.demo.service;

import com.xiazhi.demo.model.Spider;

import java.util.List;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
public interface SpiderService {

//    查询种类
    List<Spider> getSpiderList();
//    查询种类id
    Spider getSpiderId(String spiderName);
}
