package com.hms.controller;

import com.hms.ViewObject.IdName;
import com.hms.base.BaseController;
import com.hms.enums.RoomStatusEnum;
import com.hms.enums.RoomTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wb-wyh270612 on 2017/4/19.
 */
@RequestMapping("/enum")
@Controller
public class EnumController extends BaseController{
    //房间类型
    @RequestMapping("roomTypeEnum")
    public @ResponseBody List<IdName> RoomTypeEnum(){
        List<IdName> list = new ArrayList<>();
        for (RoomTypeEnum e : RoomTypeEnum.values()) {
            IdName idName = new IdName();
            idName.setKey(e.getIndex());
            idName.setValue(e.getDesc());
            list.add(idName);
        }
        return list;
    }

    //房间状态
    @RequestMapping("roomStatusEnum")
    public @ResponseBody List<IdName> RoomStatusEnum(){
        List<IdName> list = new ArrayList<>();
        for (RoomStatusEnum e : RoomStatusEnum.values()) {
            if(e.getIndex()!=-1){
            IdName idName = new IdName();
            idName.setKey(e.getIndex());
            idName.setValue(e.getDesc());
            list.add(idName);}
        }
        return list;
    }
}
