package com.xiazhi.demo.model;

import java.util.Date;

/**
 * @author   kaito kuroba
 * @Email   3118659412@qq.com
 * @since   2021/10/10
 */
public class LoginLog {

    private int userId;
    private int logId;
    private Date LoginTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Date getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(Date loginTime) {
        LoginTime = loginTime;
    }
}
