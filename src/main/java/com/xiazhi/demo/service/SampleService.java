package com.xiazhi.demo.service;

import com.xiazhi.demo.model.Sample;
import com.xiazhi.demo.model.User;
import com.xiazhi.demo.model.extension.Sample_Spider;

import java.util.HashSet;
import java.util.List;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
public interface SampleService {
    List<Sample_Spider> getSampleList();

    List<Sample_Spider> getSampleList_User(User user);

    int insertSample(Sample sample);

    //    文件数据批量上传
    List<Sample_Spider> insertSamples(List<Sample_Spider> sample_spiders) throws Exception;

    boolean updateSample(Sample sample);

    List<Sample> getSamples();

//    主页搜索查询
    List<Sample_Spider> getSelectResult(String str);

//    处理坐标
    List<Sample_Spider> getCoordinate(List<Sample_Spider> sample_spiders);
}
