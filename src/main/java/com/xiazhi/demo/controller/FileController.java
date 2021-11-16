package com.xiazhi.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.FileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiazhi.demo.model.Spider;
import com.xiazhi.demo.model.extension.Sample_Spider;
import com.xiazhi.demo.service.SampleService;
import com.xiazhi.demo.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 静态文件访问
 */
@Controller
@Slf4j
@RequestMapping("/file")
public class FileController {

    private HashMap<String,Object> hs=new HashMap<>();
    private static String pathname = "D:/Vue毕设项目/毕设后台/src/main/resources/static/";

    @Autowired
    SampleService sampleService;
    /**
     * 图片访问
     * @param response
     * @param photoName
     * @throws IOException
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void GetLogo(HttpServletResponse response , String photoName) throws IOException {
        String filename = photoName + ".jpg";  //根据用户名生成文件名

        File file = new File(pathname + "image/avatar/", filename);
        FileInputStream fis;
        fis = new FileInputStream(file);

        long size = file.length();
        byte[] data = new byte[(int) size];
        fis.read(data, 0, (int) size);
        fis.close();
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
        out.close();
    }

    /**
     * 头像更新
     * @param file
     * @param userName
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile file,String userName) throws JsonProcessingException {
        hs.clear();
        String state="0";
        if (( file !=null && ! file.isEmpty() )){
            System.out.println(userName);
            boolean res = FileUploadUtil.upload(file,userName);
            if(res){
                state="1";
            }else{
                state="-1";
            }
        }
        hs.put("userName",userName);
        hs.put("state",state);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    /**
     * excel文件下载
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadExcel")
    public ResponseEntity<byte[]> download2() throws IOException {
        File file=null;
        try {
            file = ResourceUtils.getFile(pathname + "excel/model.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return FileUploadUtil.buildResponseEntity(file);
    }

    /**
     * 文件形式批量上传数据
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public String uploadExcel(MultipartFile file) throws Exception  {
        List<Sample_Spider> dataList = new ArrayList<Sample_Spider>();;
        System.out.println("dfaf");
        hs.clear();
        String state="false";
        String name=file.getOriginalFilename();
        if(name.equals("model.xlsx")){
            dataList = FileUploadUtil.excelUpload(file.getInputStream());
            if(dataList!=null){
                dataList = sampleService.insertSamples(dataList);
            }
            state="true";
        }
        hs.put("state",state);
        hs.put("samples",dataList);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);

    }

}
