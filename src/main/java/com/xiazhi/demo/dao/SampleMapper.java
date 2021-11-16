package com.xiazhi.demo.dao;


import com.xiazhi.demo.model.Sample;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.model.extension.Sample_Spider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SampleMapper {
//  Sample_Spider总数据查询
    List<Sample_Spider> getSampleList();
//    个人上传记录查询
    List<Sample_Spider> getSampleList_user(User user);
//    数据上传
    int insertSample(Sample sample);
//    数据更改
    int updateSample(Sample sample);
//    Sample 数据查询
    List<Sample> getSample();
//    样本编号查询
    Sample checkSampleNumber(String sampleNumber);
//    主页搜索
    List<Sample_Spider> getSelectResult(String str);

}
