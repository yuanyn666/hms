package com.hms.query;

import com.hms.base.BaseQuery;

import java.util.Date;

/**
 * Created by wb-wyh270612 on 2017/4/21.
 */
public class WarehouseQuery extends BaseQuery{

    /**
     *门店编码
     */
    private String warehouseCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 门店名称
     */
    private String warehouseName;

    /**
     * 地址
     * @return
     */
    private String address;

    /**
     * 评分
     */
    private Double score;

    /**
     * 时间
     * @return
     */
    private Date createBeginDate;
    private Date createEndData;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getcreateBeginDate() {
        return createBeginDate;
    }

    public void setcreateBeginDate(Date createBeginDate) {
        this.createBeginDate = createBeginDate;
    }

    public Date getCreateEndData() {
        return createEndData;
    }

    public void setCreateEndData(Date createEndData) {
        this.createEndData = createEndData;
    }
}
