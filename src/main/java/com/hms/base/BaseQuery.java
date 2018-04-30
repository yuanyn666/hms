package com.hms.base;

import java.io.Serializable;

public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    // 起始
    private int begin;

    // 每页显示
    private int num = 10;

    // 当前页
    private int index;

    // 是否分页
    private boolean isDownload;

    // 排序规则
    private String order;
    private String sort = "DESC";

    public boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(boolean isDownload) {
        this.isDownload = isDownload;
    }

    public Integer getBegin() {
        begin = index * num;
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
