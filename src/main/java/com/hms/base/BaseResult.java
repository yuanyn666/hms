package com.hms.base;

/**
 * Created by wb-xiexiaoguang.a on 2017/3/13.
 */
public class BaseResult<T> {

    private T result;

    private boolean status = true;

    private String msg;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean success) {
        this.status = success;
    }

    public String getMsg() {

        if (getStatus() == true) {
            return null;
        }

        if (msg == null) {
            msg = "null";
        }

        return msg;
    }

    public void setMsg(Exception ex) {

        if (ex.getCause() == null) {
            this.msg = ex.getMessage();
        } else {
            this.msg = ex.getMessage() + " - " + ex.getCause();
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
