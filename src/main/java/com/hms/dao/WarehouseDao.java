package com.hms.dao;

import com.hms.entity.WarehouseDO;
import com.hms.query.WarehouseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wb-wyh270612 on 2017/4/19.
 */
public interface WarehouseDao {
    List<WarehouseDO> queryByName(@Param("warehouseName")String warehouseName);

    List<WarehouseDO> queryByCodes(List<String> codes);

    List<WarehouseDO> query(WarehouseQuery warehouseQuery);

    Integer count(WarehouseQuery warehouseQuery);

    Integer saveWarehouseDO(WarehouseDO warehouseDO);

    Integer updateWarehouse(String warehouseCode);
}
