package com.xiazhi.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiazhi.demo.model.Mail;
import com.xiazhi.demo.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
@Controller
@RequestMapping("/email")
@Slf4j
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;// 发送者


    /**
     * 邮箱验证码发送
     *
     * @param mail
     * @return
     */
    @RequestMapping("/getMail")
    @ResponseBody
    @CrossOrigin
    public String getMail(Mail mail) throws JsonProcessingException{
        log.info(mail.getUserMail());
        SimpleMailMessage message = new SimpleMailMessage();
        String code = VerifyCode(6);    //随机数生成6位验证码
        message.setFrom(sender);
        message.setTo(mail.getUserMail());
        message.setSubject("蛛毒数据网站系统");// 标题
        message.setText("【蛛毒系统网站】你的验证码为："+code+"，有效时间为5分钟(若非本人操作，可忽略该条邮件)");// 内容

        Map<String, Object> resultMap = new HashMap<>();
        try {
            javaMailSender.send(message);
            log.info("文本邮件发送成功！");
            resultMap.put("state","true");
            resultMap.put("code", MD5Utils.code(code));
        }catch (MailSendException e){
            log.error("目标邮箱不存在");
            resultMap.put("state","false");
            ObjectMapper objectMapper=new ObjectMapper();
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            log.error(e.toString());
            resultMap.put("state","failure");

        }finally {
            ObjectMapper objectMapper=new ObjectMapper();
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    /**
     * 生成6位验证码
     *
     * @param n
     * @return String
     */
    private String VerifyCode(int n){
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < n;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
//        System.out.println(sb);
        return sb.toString();
    }
}
