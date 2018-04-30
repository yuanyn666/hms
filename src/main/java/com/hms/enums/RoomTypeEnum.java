package com.hms.enums;

/**
 * Created by wb-wyh270612 on 2017/4/18.
 */
public enum RoomTypeEnum {
    /**
     * 房间类型
     */
    SINGLE(1,"单人间"),DOUBLE(2,"双人间"),FAMILY(3,"家庭套房"),DELUXE(4,"豪华套房");

    private int index;

    private String desc;

    RoomTypeEnum(int index,String desc){
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

    public static RoomTypeEnum valueof(int index){
        RoomTypeEnum[] statusEna = RoomTypeEnum.values();
        for(RoomTypeEnum status : statusEna){
            if(status.getIndex() == index){
                return status;
            }
        }
        return null;
    }
}
