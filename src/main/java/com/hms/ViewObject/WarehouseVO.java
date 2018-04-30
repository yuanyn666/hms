package com.hms.ViewObject;

import java.util.Date;

/**
 * Created by wb-wyh270612 on 2017/4/21.
 */
public class WarehouseVO {
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
     * 分店负责人
     */
    private String warehouseMg;

    /**
     *创建时间
     */
    private Date createDate;

    /**
     * 评分
     */
    private Double  score;

    /***
     * 联系电话
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

    public String getWarehouseMg() {
        return warehouseMg;
    }

    public void setWarehouseMg(String warehouseMg) {
        this.warehouseMg = warehouseMg;
    }

    public Date getCreateData() {
        return createDate;
    }

    public void setCreateData(Date createData) {
        this.createDate = createData;
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
}
