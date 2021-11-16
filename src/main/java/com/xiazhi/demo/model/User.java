package com.xiazhi.demo.model;

import java.util.Date;

public class User {
    private Integer userId;        //id标识
    private String userName;   //用户名
    private String userEmail;  //用户邮箱
    private String userPass;   //密码
    private String roleId;     //用户角色
    private Date upLoginTime;  //用户最近登录时间
    private String registTime;
    private String userPhoto;

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpLoginTime() {
        return upLoginTime;
    }

    public void setUpLoginTime(Date upLoginTime) {
        this.upLoginTime = upLoginTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
