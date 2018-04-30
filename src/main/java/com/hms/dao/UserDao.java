package com.hms.dao;

import com.hms.entity.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by D on 2017/4/9.
 */
public interface UserDao {
    Integer saveUserDO(UserDO userDO);

    UserDO queryByAccountNumAndJobNum(@Param("accountNum") String accountNum, @Param("jobNum") String jobNum);


}
