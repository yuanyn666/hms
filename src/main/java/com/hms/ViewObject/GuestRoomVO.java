package com.hms.ViewObject;

import java.util.Date;

/**
 * Created by wb-wyh270612 on 2017/4/21.
 */
public class GuestRoomVO {
    private String id;

    /**
     * 房间类型
     */
    private Integer roomType;
    private String roomTypeText;

    /**
     * 房间号
     */
    private String roomNum;

    /**
     * 楼层
     */
    private String floorNum;

    /**
     * 设备
     */
    private String roomDevice;

    /**
     *状态
     */
    private Integer status;
    private String  statusText;

    /***
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
    private String warehouseName;

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

    public String getRoomTypeText() {
        return roomTypeText;
    }

    public void setRoomTypeText(String roomTypeText) {
        this.roomTypeText = roomTypeText;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getRoomDevice() {
        return roomDevice;
    }

    public void setRoomDevice(String roomDevice) {
        this.roomDevice = roomDevice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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
}
