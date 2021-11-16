package com.xiazhi.demo.utils;

import com.xiazhi.demo.model.extension.Sample_Spider;
import com.xiazhi.demo.service.SpiderService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
public class FileUploadUtil {

    private static final String pathname = "D:/Vue毕设项目/毕设后台/src/main/resources/static/image/avatar/";

    /**
     * 上传文件
     *
     * @param multipartFile
     * @return 文件存储路径
     */
    public static boolean upload(MultipartFile multipartFile,String userName) {
        String filename = multipartFile.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        String filePath = pathname + userName + suffixName;
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 文件下载工具类
     * @param file
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> buildResponseEntity(File file) throws IOException {
        byte[] body = null;
        //获取文件
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        //设置文件类型
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置Http状态码
        HttpStatus statusCode = HttpStatus.OK;
        //返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }


    public static List<Sample_Spider> excelUpload(InputStream inputStream) throws IOException, InvalidFormatException {
        List<Sample_Spider> dataList = null;
        Workbook wb = WorkbookFactory.create(inputStream);
        // 获取第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // 获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        System.out.println(rownum);
        // 获取第一行
        Row row = sheet.getRow(0);
        // 存放表中的数据
        dataList = new ArrayList<Sample_Spider>();
        // 循环行
        for (int i = 2; i < rownum-1; i++) {
            Sample_Spider sample_spider = new Sample_Spider();
            row = sheet.getRow(i);
            if (row != null) {
                System.out.println(row.getCell(4).toString());
                sample_spider.setSampleNumber(row.getCell(0).toString());
                sample_spider.setSpiderName(row.getCell(1).toString());
                sample_spider.setUserName(row.getCell(2).toString());
                sample_spider.setSampleGenetic(row.getCell(3).toString());
                sample_spider.setSampleTime(row.getCell(4).toString());
                sample_spider.setSampleLocations(row.getCell(5).toString());
                sample_spider.setSampleList(row.getCell(6).toString());
                sample_spider.setSampleLength(sample_spider.getSampleList().length());

                dataList.add(sample_spider);
            } else {
                continue;
            }
        }
        return dataList;
    }


//    private static final String path="D:/Vue毕设项目/毕设后台/大疣蛛DNA实验采集地点统计.xlsx";

/**
 * 数据大量上传
 */
//    public static ArrayList<Sample>  excel_file() {
//        ArrayList<Sample> samples=new ArrayList<>();
//        FileInputStream fileInputStream = null;
//        String sheetName="Sheet1";
//        XSSFSheet sheet;
//        try {
//            fileInputStream = new FileInputStream(path);
//            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
//            //获取sheet
//            sheet = sheets.getSheet(sheetName);
//            //获取行数
//            int rows = sheet.getPhysicalNumberOfRows();
//            int spiderId=1,userId=6;
//            for(int i=1;i<rows;i+=2){
//                Sample sample=new Sample();
//                int k=i;
//                System.out.println(k+":");
//                if(i==143)
//                    spiderId=2;
//                if(i==153)
//                    spiderId=3;
//                if(i==157)
//                    spiderId=4;
//                if(i==161)
//                    spiderId=5;
//                if(i==165){
//                    userId=25;
//                    spiderId=6;
//                }
//                if(i==193)
//                    spiderId=7;
//                sample.setSpiderId(spiderId);
//                sample.setUserId(userId);
//                String s="";
//                while (k<=i+1) {
//                    //获取列数
//                    XSSFRow row = sheet.getRow(k);
//                    if (k % 2 != 0) {
////                        采集地点
//                        s = row.getCell(0).toString();
//                        String pattern = "日(.*)采集";
//                        Pattern r = Pattern.compile(pattern);
//                        Matcher m = r.matcher(row.getCell(0).toString());
//                        if (m.find()) {
//                            sample.setSampleLocations(m.group(1));
//                        } else {
//                            pattern = "月(.*)采集";
//                            r = Pattern.compile(pattern);
//                            m = r.matcher(row.getCell(0).toString());
//                            if(m.find()){
//                                sample.setSampleLocations(m.group(1));
//                            }else{
//                                System.out.println("Not Find!");
//                            }
//                        }
//
////                        采集时间
//                        DataFormatter dataFormatter = new DataFormatter();
//                        String tel = dataFormatter.formatCellValue(row.getCell(1));
//                        String regex = "(.{4})(.{2})(.{2})";
//                        boolean b = Pattern.matches(regex, tel);
//                        tel = tel.replaceAll(regex, "$1-$2-$3") + " 00:00:00";
//                        sample.setSampleTime(tel);
////
////                        采集序列
//                        sample.setSampleList(row.getCell(2).toString());
//                        sample.setSampleLength(sample.getSampleList().length());
//
//
//                    } else {
//                        sample.setSampleInstruct(s + " " + row.getCell(0).toString());
//                        sample.setSampleNumber(row.getCell(1).toString());
////                        基因类型
//                        String m = row.getCell(1).toString();
//                        String[] res = m.split(" ");
//                        sample.setSampleGenetic(res[1]);
//                    }
//                    k++;
//                }
//                samples.add(sample);
//            }
//            return samples;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    /**
     * 根据城市名称查询所在经纬度
     * @param addr
     * 查询的地址
     * @return
     * @throws IOException
     */
    public static String[] getCoordinate(String addr) throws IOException {
        String lng = null;//经度
        String lat = null;//纬度
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        }catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = "fym8ZmhmhKqnG5ni8EDRAELuzMYTF7mg";
        String url = String .format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                int count = 1;
                while((data= br.readLine())!=null){
                    if(count==5){
                        lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));//经度
                        count++;
                    }else if(count==6){
                        lat = data.substring(data.indexOf(":")+1);//纬度
                        count++;
                    }else{
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(insr!=null){
                insr.close();
            }
            if(br!=null){
                br.close();
            }
        }
        return new String[]{lng,lat};
    }


}
