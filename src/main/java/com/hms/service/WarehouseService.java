package com.hms.service;

import com.hms.ViewObject.WarehouseVO;
import com.hms.base.PageResult;
import com.hms.entity.WarehouseDO;
import com.hms.query.WarehouseQuery;

import java.util.List;

/**
 * Created by wb-wyh270612 on 2017/4/19.
 */
public interface WarehouseService {
     List<Object> getCodeName(String name);

     PageResult<WarehouseVO> query(WarehouseQuery warehouseQuery) throws Exception;

     Boolean saveWarehouse(WarehouseDO warehouseDO);

     Boolean updateWarehouse(String warehouseCode);
}
