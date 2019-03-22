package com.example.demo.common;

/**
 * Created by zyb on 2018/9/13.
 */
public class Page {

    private Integer rows;

    private Integer page = 1;//页码，默认是第一页

    private Integer totalRecord;//总记录数

    private Integer totalNumber;//总页数

    private Integer dbIndex;

    private Integer dbNumber;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }

    public Integer getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(Integer dbNumber) {
        this.dbNumber = dbNumber;
    }
}
