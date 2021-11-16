package com.xiazhi.demo;


import com.xiazhi.demo.model.Sample;
import com.xiazhi.demo.service.SampleService;
import com.xiazhi.demo.utils.FileUploadUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    SampleService sampleService;
    @Test
    public void getLearn() throws IOException {
//        ArrayList<Sample> samples=new ArrayList<>();
//        samples=FileUploadUtil.excel_file();
//
//        for(Sample e : samples){
//            System.out.println(e.getSampleNumber());
//            sampleService.insertSample(e);
//        }
//        int k=0;
//        while(k++<20){
//            List<Sample> samples= sampleService.getSamples();
//            int i=1;
//            for(Sample e : samples){
//                System.out.print(i++ +":"+ e.getSampleLocations()+":");
//                String []s= FileUploadUtil.getCoordinate(e.getSampleLocations());
//                if(s[0].length()<15&&Float.parseFloat(s[1])<30.0){
//                    System.out.println(s[0]+','+s[1]);
//                    e.setSampleJdu(s[0]);
//                    e.setSampleWdu(s[1]);
//                    sampleService.updateSample(e);
//                    System.out.println(e.getSampleJdu());
//                }else{
//                    System.out.println("Not");
//                }
//            }
//        }
    }
}
