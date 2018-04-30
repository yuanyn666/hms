package com.hms.enums;

/**
 * Created by wb-wyh270612 on 2017/4/14.
 */
public enum RoomStatusEnum {
    /**
     * 房间状态
     */
    FREE(1,"空闲"),USE(2,"使用"),CLEAN(3,"清洁"),STOP(-1,"停用");

    private int index;

    private String desc;

    RoomStatusEnum(int index,String desc){
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static RoomStatusEnum valueof(int index){
        RoomStatusEnum[] statusEna = RoomStatusEnum.values();
        for(RoomStatusEnum status : statusEna){
            if(status.getIndex() == index){
                return status;
            }
        }
        return null;
    }
}
