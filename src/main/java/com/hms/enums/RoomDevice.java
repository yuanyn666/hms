package com.hms.enums;

/**
 * Created by wb-wyh270612 on 2017/4/20.
 */
public enum RoomDevice {
    WINDOW(1,"有窗"),BIG_BED(2,"大床"),DOUBLE_BED(3,"双床"),WIRELESS(4,"无线"),BATHROOM(5,"独卫"),TV(6,"液晶电视"),SOCK(7,"零食");
    private int index;

    private String desc;

    RoomDevice(int index, String desc){
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

    public static RoomDevice valueof(int index){
        RoomDevice[] statusEna = RoomDevice.values();
        for(RoomDevice status : statusEna){
            if(status.getIndex() == index){
                return status;
            }
        }
        return null;
    }
}
