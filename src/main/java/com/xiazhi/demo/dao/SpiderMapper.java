package com.xiazhi.demo.dao;

import com.xiazhi.demo.model.Spider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
@Mapper
@Repository
public interface SpiderMapper {

//    查询蜘蛛种类
    List<Spider> getSpiderList();

//    查询种类id
    Spider getSpiderId(String spiderName);
}
