package com.hms.base;

import java.util.List;

/**
 * Created by wb-wyh270612 on 2017/4/21.
 */
public class PageResult<T> {
    List<T> list;
    private Integer index;
    private Integer begin;
    private Integer num;
    private int totalNum;
    private String order;

    public PageResult() {
    }

    public static <T> PageResult<T> create(List<T> list, int index, int num, int totalNum) {
        PageResult page = new PageResult();
        page.setList(list);
        page.setIndex(Integer.valueOf(index));
        page.setNum(Integer.valueOf(num));
        page.setTotalNum(totalNum);
        return page;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getBegin() {
        return this.begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
