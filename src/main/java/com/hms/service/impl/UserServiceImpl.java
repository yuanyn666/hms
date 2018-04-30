package com.hms.service.impl;

import com.hms.dao.UserDao;
import com.hms.entity.UserDO;
import com.hms.service.UserService;
import com.hms.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by D on 2017/4/10.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public UserDO register(UserDO userDO) {
        MD5Utils.Secret secret = MD5Utils.getSecret(userDO.getPassword());
        userDO.setSalt(secret.getSalt());
        userDO.setPassword(secret.getEncryptionData());
        String uuid = MD5Utils.getUUID();
        userDO.setId(uuid);
        //员工账号状态
        userDO.setStatus(1);
        //职位
        userDO.setPosition(1);
        //所属门店
        userDO.setWarehouseCode("zz_001");
        userDO.setCreateDate(new Date());
        Integer integer = userDao.saveUserDO(userDO);
        if (integer>0)
            return userDO;
        return new UserDO();
    }

    @Override
    public UserDO login(String accountNum, String password) {
        UserDO userDO = userDao.queryByAccountNumAndJobNum(accountNum,null);
        if(userDO!=null){
            if(password==MD5Utils.getSecret(password).getOriginalData())
                return userDO;
            return new UserDO();
        }else{
            return new UserDO();
        }
    }

    @Override
    public Boolean checkAccountNum(String accountNum, String jobNum) {
        UserDO userDO = userDao.queryByAccountNumAndJobNum(accountNum, jobNum);
        if(userDO!=null)
            return false;
        return true;
    }
}
