package com.hms.enums;

/**
 * Created by wb-wyh270612 on 2017/4/14.
 */
public enum PositionEnum {
    /**
     * 人员职位
     */
    EMP(1,"员工"),MG(2,"经理");
    private int index;

    private String desc;

    PositionEnum(int index, String desc){
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

    public static PositionEnum valueof(int index){
        PositionEnum[] statusEna = PositionEnum.values();
        for(PositionEnum status : statusEna){
            if(status.getIndex() == index){
                return status;
            }
        }
        return null;
    }
}
