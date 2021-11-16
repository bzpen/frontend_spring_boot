package com.xiazhi.demo.service.impl;

import com.xiazhi.demo.dao.SpiderMapper;
import com.xiazhi.demo.model.Spider;
import com.xiazhi.demo.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.util.List;

@Service
@Slf4j
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    SpiderMapper spiderMapper;

    /**
     *获取所有种类
     * @return
     */
    @Override
    public List<Spider> getSpiderList() {
        try{
            List<Spider> spiders=spiderMapper.getSpiderList();
            if(spiders!=null)
                return spiders;
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Spider getSpiderId(String spiderName) {
        try{
            Spider spiders=spiderMapper.getSpiderId(spiderName);
            if(spiders!=null)
                return spiders;
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
