package com.hms.entity;

import java.util.Date;

/**
 * Created by D on 2017/4/9.
 */
public class UserDO {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String employeeName;

    /**
     * 工号
     */
    private String jobNum;

    /**
     * 职位 1 。员工 2 经理 3 部长
     */
    private Integer position;

    /**
     * 账号
     */
    private String accountNum;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态 1
     */
    private Integer status;

    /**
     * 所属门店
     */
    private String warehouseCode;

    /**
     * 随机数
     */
    private String salt;

    public UserDO() {

    }

    public UserDO(String id, String employeeName, String jobNum, Integer position, String accountNum, String password, Date createDate, Integer status, String warehouseCode) {
        this.id = id;
        this.employeeName = employeeName;
        this.jobNum = jobNum;
        this.position = position;
        this.accountNum = accountNum;
        this.password = password;
        this.createDate = createDate;
        this.status = status;
        this.warehouseCode = warehouseCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
