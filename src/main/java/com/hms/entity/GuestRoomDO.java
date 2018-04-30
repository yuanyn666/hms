package com.hms.entity;

import java.util.Date;

/**
 * Created by D on 2017/4/10.
 */
public class GuestRoomDO {
    /**
     * id
     */
    private String id;

    /**
     * 房间类型 1. 单人 2.双人 3.家庭套房 4 豪华套房
     */
    private Integer roomType;

    /**
     * 房间号
     */
    private String roomNum;

    /**
     * 楼层
     */
    private Integer floorNum;

    /**
     * 设备
     */
    private String device;

    /**
     * 状态 1.空闲  2.占用 3等待
     */
    private Integer status;

    /**
     * 价格
     */
    private Double privice;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 门店编码
     */
    private String warehouseCode;

    /**
     * 面积
     */
    private Double roomArea;

    /**
     * 单位
     */
    private String  uint;

    /**
     * 描述
     */
    private String roomDescribe;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    public GuestRoomDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPrivice() {
        return privice;
    }

    public void setPrivice(Double privice) {
        this.privice = privice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Double roomArea) {
        this.roomArea = roomArea;
    }

    public String getUint() {
        return uint;
    }

    public void setUint(String uint) {
        this.uint = uint;
    }

    public String getRoomDescribe() {
        return roomDescribe;
    }

    public void setRoomDescribe(String roomDescribe) {
        this.roomDescribe = roomDescribe;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public GuestRoomDO(String id, Integer roomType, String roomNum, Integer floorNum, String device, Integer status, Double privice, Date createDate, String warehouseCode, Double roomArea, String uint, String roomDescribe, Date beginDate, Date endDate) {
        this.id = id;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.floorNum = floorNum;
        this.device = device;
        this.status = status;
        this.privice = privice;
        this.createDate = createDate;
        this.warehouseCode = warehouseCode;
        this.roomArea = roomArea;
        this.uint = uint;
        this.roomDescribe = roomDescribe;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}
