package com.example.demo.entity;

import java.util.Date;

/**
 * Created by zyb on 2018/9/17.
 */
public class Log {

    private Date createTime;

    private String type;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
