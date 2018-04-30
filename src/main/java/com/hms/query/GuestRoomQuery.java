package com.hms.query;

import com.hms.base.BaseQuery;

/**
 * Created by wb-wyh270612 on 2017/4/21.
 */
public class GuestRoomQuery extends BaseQuery{

    /**
     *房间类型
     */
    private Integer roomType;

    /**
     * 房间状态
     */
    private Integer status;

    /**
     * 房间号
     */
    private String roomNum;


    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
