package com.hms.service;

import com.hms.ViewObject.GuestRoomVO;
import com.hms.base.PageResult;
import com.hms.entity.GuestRoomDO;
import com.hms.query.GuestRoomQuery;
import java.util.List;

/**
 * Created by D on 2017/4/10.
 */
public interface GuestRoomService {
    /**
     * 新增客房
     * @param guestRoomDO
     * @return
     */
   Boolean saveGuestRoomDO(GuestRoomDO guestRoomDO);

    /**
     * 根据房间号查询客房信息
     */
    GuestRoomDO queryByRoomNum(String roomNum);

    /**
     * 修改房间状态
     * @param roomNum
     * @return
     */
    Integer removeByRoomNum(List<String> roomNum);

    /**
     *查询客房
     * @param guestRoomQuery
     * @return
     * @throws Exception
     */

    PageResult<GuestRoomVO> query(GuestRoomQuery guestRoomQuery) throws Exception;

    /**
     * 修改客房信息
     * @param guestRoomDO
     * @return
     */
    Boolean update(GuestRoomDO guestRoomDO);
}
