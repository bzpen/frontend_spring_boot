package com.xiazhi.demo.model.extension;

import com.xiazhi.demo.model.Sample;

/**
 *  扩展实体类，用于多表数据合并
 *
 */
public class Sample_Spider extends Sample {

    private String userName;
    private String spiderName;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }
}
