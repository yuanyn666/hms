package com.hms.controller;

import com.hms.base.BaseController;
import com.hms.entity.GuestRoomDO;
import com.hms.query.GuestRoomQuery;
import com.hms.service.GuestRoomService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by wb-wyh270612 on 2017/4/18.
 */
@RequestMapping("guestroom")
@Controller
public class GuestRoomController extends BaseController{
    @Resource
    private GuestRoomService guestRoomService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public @ResponseBody Object saveGuestRoom(@Param("roomNum")String roomNum, @Param("roomType")Integer roomType, @Param("floorNum")Integer floorNum,
                         @Param("privice")Double privice, @Param("warehouseCode")String warehouseCode,
                         @Param("roomArea")Double roomArea, @Param("roomDescribe")String roomDescribe,
                         @Param("roomDevice")String [] roomDevice){
        GuestRoomDO guestRoomDO = null;
        try {
            checkParams(roomNum,roomDescribe,roomType,roomArea,floorNum,privice,roomDevice,warehouseCode);
            GuestRoomDO guestRoomDO1 = guestRoomService.queryByRoomNum(roomNum);
            if (null!=guestRoomDO1){
            return fail(new RuntimeException("该房间号已存在！"));}
            guestRoomDO = new GuestRoomDO();
            guestRoomDO.setWarehouseCode(warehouseCode);
            String toS = SListToS(Arrays.asList(roomDevice));
            guestRoomDO.setDevice(toS);
            guestRoomDO.setFloorNum(floorNum);
            guestRoomDO.setPrivice(privice);
            guestRoomDO.setRoomArea(roomArea);
            guestRoomDO.setRoomDescribe(roomDescribe);
            guestRoomDO.setRoomNum(roomNum);
            guestRoomDO.setRoomType(roomType);
            Boolean aBoolean = guestRoomService.saveGuestRoomDO(guestRoomDO);
            return success(aBoolean);
        } catch (Exception e) {
            return fail(e);
        }
    }

    @RequestMapping("/queryGuestRoom")
    public @ResponseBody Object queryGuestRoom(@Param("index")Integer index,@Param("desc")Object desc,
                                               @Param("roomType")Integer roomType,@Param("status")Integer status,@Param("roomNum")String roomNum){

       try {
        checkParams(index);
        GuestRoomQuery guestRoomQuery = new GuestRoomQuery();
        guestRoomQuery.setIndex(index);
        if(roomType!=null)
        guestRoomQuery.setRoomType(roomType);
        if (StringUtils.isNotBlank(roomNum))
        guestRoomQuery.setRoomNum(roomNum);
        if (status!=null)
        guestRoomQuery.setStatus(status);
        guestRoomQuery.setOrder("create_date");
             return success(guestRoomService.query(guestRoomQuery));
        } catch (Exception e) {
            return fail(e);
        }
    }

    @RequestMapping("remove")
    public @ResponseBody Object remove(@Param("roomNum")String roomNum){
        try {
            checkParams(roomNum);
            Integer integer = guestRoomService.removeByRoomNum(Arrays.asList(roomNum));
            if (integer!=0&&integer!=null)
            return  success(integer);
            return fail(new RuntimeException());
        } catch (Exception e) {
            return fail(e);
        }
    }

    @RequestMapping("/queryByRoomNum")
    public @ResponseBody Object queryByRoomNum(@Param("roomNum")String roomNum){
        try {
            checkParams(roomNum);
            GuestRoomDO guestRoomDO = guestRoomService.queryByRoomNum(roomNum);
            if (null==guestRoomDO)
                return fail(null);
            return success(guestRoomDO);
        } catch (Exception e) {
            return fail(e);
        }
    }

    @RequestMapping("/checkRoomNum")
    public @ResponseBody Object cheackRoomNum(@Param("roomNum")String roomNum){
        try {
            checkParams(roomNum);
            GuestRoomDO guestRoomDO = guestRoomService.queryByRoomNum(roomNum);
            if (null==guestRoomDO)
                return success(null);
            return fail(new RuntimeException("该房间号已存在！"));
        } catch (Exception e) {
            return fail(e);
        }
    }

    @RequestMapping("/update")
    public @ResponseBody Object update(@Param("roomNum")String roomNum, @Param("roomType")Integer roomType, @Param("floorNum")Integer floorNum,
                                       @Param("privice")Double privice, @Param("warehouseCode")String warehouseCode,
                                       @Param("roomArea")Double roomArea, @Param("roomDescribe")String roomDescribe,
                                       @Param("roomDevice")String [] roomDevice){
        checkParams(roomNum);
        try {
            GuestRoomDO guestRoomDO;
            guestRoomDO = new GuestRoomDO();
            guestRoomDO.setWarehouseCode(warehouseCode);
            String toS = SListToS(Arrays.asList(roomDevice));
            guestRoomDO.setDevice(toS);
            guestRoomDO.setFloorNum(floorNum);
            guestRoomDO.setPrivice(privice);
            guestRoomDO.setRoomArea(roomArea);
            guestRoomDO.setRoomDescribe(roomDescribe);
            guestRoomDO.setRoomNum(roomNum);
            guestRoomDO.setRoomType(roomType);
            Boolean update = guestRoomService.update(guestRoomDO);
            return success(update);
        } catch (Exception e) {
            return fail(e);
        }
    }

}
