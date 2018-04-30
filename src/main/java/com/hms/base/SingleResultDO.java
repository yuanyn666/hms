package com.hms.base;

/**
 * Created by wb-wyh270612 on 2017/5/16.
 */
public class SingleResultDO<T> extends BaseResultDO {

    private T result;

    private Long totelNum;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTotelNum() {
        return totelNum;
    }

    public void setTotelNum(Long totelNum) {
        this.totelNum = totelNum;
    }
}
