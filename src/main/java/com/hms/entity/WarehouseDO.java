package com.hms.entity;

import java.util.Date;

/**
 * Created by D on 2017/4/10.
 */
public class WarehouseDO {
    /**
     * id
     */
    private String id;

    /**
     * 门店名称
     */
    private String warehouseName;

    /**
     * 门店编码
     */
    private String warehouseCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 门店经理
     */
    private String userId;

    /**
     * 状态
     */
    private Integer status;

    /**
     *评分
     */
    private Double score;

    /**
     * 电话
     */
    private String tel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public WarehouseDO() {
    }

    public WarehouseDO(String id, String warehouseName, String warehouseCode, String address, Date createDate, String userId, Integer status) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.warehouseCode = warehouseCode;
        this.address = address;
        this.createDate = createDate;
        this.userId = userId;
        this.status = status;
    }
}
