package com.xiazhi.demo.service.impl;

import com.xiazhi.demo.dao.SampleMapper;
import com.xiazhi.demo.model.Sample;
import com.xiazhi.demo.model.Spider;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.model.extension.Sample_Spider;
import com.xiazhi.demo.service.SampleService;
import com.xiazhi.demo.service.SpiderService;
import com.xiazhi.demo.service.UserService;
import com.xiazhi.demo.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
@Service
@Slf4j
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleMapper sampleMapper;

    @Autowired
    SpiderService spiderService;

    @Autowired
    UserService userService;

    /**
     * 数据查询
     * @return
     */
    @Override
    public List<Sample_Spider> getSampleList() {
        try{
            List<Sample_Spider> samples = sampleMapper.getSampleList();
            if(samples!=null){
                return samples;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info(e.toString());
            return null;
        }
    }

    /**
     *数据上传
     * @param sample
     * @return
     */
    @Override
    public int insertSample(Sample sample) {
        try{
            String []s= FileUploadUtil.getCoordinate(sample.getSampleLocations());
            System.out.println(s[0]+','+s[1]);
            if(s[0].length()<15&&Float.parseFloat(s[1])<30.0){
                    sample.setSampleJdu(s[0]);
                    sample.setSampleWdu(s[1]);
                    Sample sample1=sampleMapper.checkSampleNumber(sample.getSampleNumber());
                    if(sample1==null){
                        int result = sampleMapper.insertSample(sample);
                        if(result>0){
                            return 1;
                        }
                        return 0;
                    }else{
                        return -1;
                    }
            }else{
                return -2;
            }

        }catch (Exception e){
            System.out.println(e);
            return -1;
        }
    }

    /**
     * 数据更改
     * @param sample
     * @return
     */
    @Override
    public boolean updateSample(Sample sample) {
        try{
            int result = sampleMapper.updateSample(sample);
            if(result>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    /**
     * 用户上传记录查询
     * @param user
     * @return
     */
    @Override
    public List<Sample_Spider> getSampleList_User(User user) {
        try{
            List<Sample_Spider> samples = sampleMapper.getSampleList_user(user);
            if(samples!=null){
                return samples;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info(e.toString());
            return null;
        }
    }


    /**
     * 坐标查询/更新
     */
    @Override
    public List<Sample> getSamples() {
        try{
            List<Sample> samples = sampleMapper.getSample();
            if(samples!=null){
                return samples;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    /**
     * 文件数据批量上传
     * @param sample_spiders
     * @return
     */
    @Override
    public List<Sample_Spider> insertSamples(List<Sample_Spider> sample_spiders) throws Exception{
        List<Sample_Spider> failList=new ArrayList<Sample_Spider>();
        for(Sample_Spider sample_spider : sample_spiders){
            try{
                Sample sample1=sampleMapper.checkSampleNumber(sample_spider.getSampleNumber());
                if(sample1!=null){
                    sample_spider.setSampleList("样本编号重复，请检查重试！");
                    failList.add(sample_spider);
                    continue;
                }
            }catch (Exception e){
                sample_spider.setSampleList("上传异常，请检查重试！");
                failList.add(sample_spider);
                continue;
            }
//            上传者id
            User user=new User();
            user.setUserName(sample_spider.getUserName());
            user=userService.getUser(user);
            if(user==null){
                sample_spider.setSampleList("上传者用户名错误！");
                failList.add(sample_spider);
                continue;
            }
            sample_spider.setUserId(user.getUserId());
//            种类id计算
            Spider spider = spiderService.getSpiderId(sample_spider.getSpiderName());
            if(spider==null){
                sample_spider.setSampleList("种类不存在或种类其他原因！");
                failList.add(sample_spider);
                continue;
            }
            sample_spider.setSpiderId(spider.getSpiderId());
//           坐标计算
            String []s= FileUploadUtil.getCoordinate(sample_spider.getSampleLocations());
            System.out.println(s[0]+','+s[1]);
            if(s[0].length()<15&&Float.parseFloat(s[1])<30.0){
                try{
                    sample_spider.setSampleJdu(s[0]);
                    sample_spider.setSampleWdu(s[1]);
                    if(this.insertSample(sample_spider)!=1)
                        failList.add(sample_spider);
                }catch (Exception e){
                    System.out.println(e);
                    failList.add(sample_spider);
                }
            }else{
                sample_spider.setSampleList("经纬度计算失败，请重试（可能是系统原因或者地点名存在歧义）！");
                failList.add(sample_spider);
            }
        }
        return failList;
    }

    /**
     * 主页搜索
     * @param str
     * @return
     */
    @Override
    public List<Sample_Spider> getSelectResult(String str) {
        try{
            List<Sample_Spider> samples = sampleMapper.getSelectResult(str);
            if(samples!=null){
                return samples;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info(e.toString());
            return null;
        }
    }

    /**
     * 坐标处理
     * @param sample_spiders
     * @return
     */
    @Override
    public List<Sample_Spider> getCoordinate(List<Sample_Spider> sample_spiders) {
        HashSet<String> sample_spiders1=new HashSet<String>();
        List<Sample_Spider> sample_spiders2=new ArrayList<Sample_Spider>();
        HashMap<String,Integer> hs=new HashMap<>();
        try{
            for(Sample_Spider e : sample_spiders){
                if(sample_spiders1.add(e.getSampleLocations())){
                    hs.put(e.getSampleLocations(),1);
                    sample_spiders2.add(e);
                }else{
                    hs.put(e.getSampleLocations(),hs.get(e.getSampleLocations())+1);
                }
            }
            for(Sample_Spider e:sample_spiders2){
                e.setNum(hs.get(e.getSampleLocations()));
            }
            return sample_spiders2;
        }catch (Exception e){
            log.info(e.toString());
            return null;
        }
    }
}
