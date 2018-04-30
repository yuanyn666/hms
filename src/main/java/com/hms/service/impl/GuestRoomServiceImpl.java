package com.hms.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hms.ViewObject.GuestRoomVO;
import com.hms.base.PageResult;
import com.hms.dao.GuestRoomDao;
import com.hms.dao.WarehouseDao;
import com.hms.entity.GuestRoomDO;
import com.hms.entity.WarehouseDO;
import com.hms.enums.RoomDevice;
import com.hms.enums.RoomStatusEnum;
import com.hms.enums.RoomTypeEnum;
import com.hms.query.GuestRoomQuery;
import com.hms.service.GuestRoomService;
import com.hms.util.CommonUtils;
import com.hms.util.CommonUtils.CallBack;
import com.hms.util.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wb-wyh270612 on 2017/4/18.
 */
@Service
public class GuestRoomServiceImpl implements GuestRoomService {
    @Resource
    private GuestRoomDao guestRoomDao;
    @Resource
    private WarehouseDao warehouseDao;
    @Override
    public Boolean saveGuestRoomDO(GuestRoomDO guestRoomDO) {
        String uuid = MD5Utils.getUUID();
        guestRoomDO.setId(uuid);
        //新增客房状态为空闲
        guestRoomDO.setStatus(1);
        //创建时间
        guestRoomDO.setCreateDate(new Date());
        //起始时间
        guestRoomDO.setBeginDate(new Date());
        //大小单位
        guestRoomDO.setUint("㎡");
        Integer integer = guestRoomDao.saveGuestRoomDO(guestRoomDO);
        if (integer!=null||integer==0)
            return false;
        return true;
    }

    @Override
    public GuestRoomDO queryByRoomNum(String roomNum) {
        return guestRoomDao.queryByRoomNum(roomNum);
    }

    @Override
    public Integer removeByRoomNum(List<String> roomNum) {
        return guestRoomDao.remove(roomNum);
    }

    @Override
    public PageResult<GuestRoomVO> query(GuestRoomQuery guestRoomQuery) throws Exception {
        PageResult<GuestRoomDO> guestRoomDOPageResult = new PageResult<>();
        List<GuestRoomDO> guestRoomDOS = guestRoomDao.queryByQuery(guestRoomQuery);
        guestRoomDOPageResult.setIndex(guestRoomQuery.getIndex());
        guestRoomDOPageResult.setList(guestRoomDOS);
        guestRoomDOPageResult.setTotalNum(guestRoomDao.count(guestRoomQuery));
        guestRoomDOPageResult.setNum(guestRoomQuery.getNum());
        ArrayList<String> codes = Lists.newArrayList();
        final Map<String,String> idName = Maps.newHashMap();
        for (GuestRoomDO guestRoomDO:guestRoomDOS){
            codes.add(guestRoomDO.getWarehouseCode());
            List<Integer> list = CommonUtils.stringToList(guestRoomDO.getDevice(), Integer.class);
            String d = "";
            for (Integer i : list) {
                String desc = RoomDevice.valueof(i).getDesc();
                d += desc + ",";
            }
            idName.put(guestRoomDO.getId(),d);
        }
        final Map<String, String> nameCode = getNameCode(codes);

        PageResult<GuestRoomVO> rvs = CommonUtils.dbToVo(guestRoomDOPageResult, GuestRoomVO.class, new CallBack<GuestRoomDO, GuestRoomVO>() {
            @Override
            public void execute(GuestRoomDO db, GuestRoomVO vo) {
                vo.setRoomNum(db.getRoomNum());
                vo.setRoomType(db.getRoomType());
                vo.setRoomTypeText(RoomTypeEnum.valueof(db.getRoomType()).getDesc());
                vo.setFloorNum(db.getFloorNum().toString());
                vo.setStatus(db.getStatus());
                vo.setStatusText(RoomStatusEnum.valueof(db.getStatus()).getDesc());
                vo.setId(db.getId());
                vo.setCreateDate(db.getCreateDate());
                vo.setPrivice(db.getPrivice());
                vo.setRoomArea(db.getRoomArea());
                vo.setUint(db.getUint());
                vo.setWarehouseCode(db.getWarehouseCode());
                if (!nameCode.isEmpty() && nameCode != null)
                    vo.setWarehouseName(db.getWarehouseCode() != null ? nameCode.get(db.getWarehouseCode()) : "");
                vo.setRoomDescribe(db.getRoomDescribe());
                if (!idName.isEmpty() && idName != null)
                    vo.setRoomDevice(db.getId() != null ? idName.get(db.getId()) : "");
            }
        });

        return rvs;
    }

    @Override
    public Boolean update(GuestRoomDO guestRoomDO) {
        Integer integer = guestRoomDao.update(guestRoomDO);
        if (integer!=null||integer==0)
            return false;
        return true;
    }

    private Map<String,String> getNameCode(List<String> codes){
        List<WarehouseDO> warehouseDOS = warehouseDao.queryByCodes(codes);
        HashMap<String, String> codeNameMap = Maps.newHashMap();
        for (WarehouseDO warehouseDO:warehouseDOS){
            codeNameMap.put(warehouseDO.getWarehouseCode(),warehouseDO.getWarehouseName());
        }
        return codeNameMap;
    }
}
