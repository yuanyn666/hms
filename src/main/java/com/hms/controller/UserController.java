package com.hms.controller;

import com.hms.base.BaseController;
import com.hms.entity.UserDO;
import com.hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by D on 2017/4/10.
 */
@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("checkAccountNum")
    public @ResponseBody Object checkAccountNum(String accountNum,String jobNum){
        try {
            Boolean aBoolean = userService.checkAccountNum(accountNum, jobNum);
            return success(aBoolean);
        } catch (Exception e) {
           return fail(e);
        }
    }

    @RequestMapping("register")
    public @ResponseBody Object register(String employeeName, String jobNum, String accountNum, String password){
        try {
            checkParams(employeeName,jobNum,accountNum,password);
            UserDO userDO = new UserDO();
            userDO.setAccountNum(accountNum);
            userDO.setEmployeeName(employeeName);
            userDO.setJobNum(jobNum);
            userDO.setPassword(password);
            UserDO aDo = userService.register(userDO);
            if (aDo==null)
               throw new RuntimeException("插入错误");
            return success(aDo);
        } catch (Exception e) {
            return fail(e);
        }
    }

    @RequestMapping("login")
    public @ResponseBody Object login(String accountNum,String password,String validate){
        try {
            checkParams(accountNum,password,validate);
            Object validationCode = session.getAttribute("validationCode");
            if(!session.getAttribute("validationCode").equals(validate))
                throw new RuntimeException("验证码不正确！");
            UserDO userDO = userService.login(accountNum, password);
            if (userDO==null||userDO.getId()==null) {
                throw  new RuntimeException("账号或者密码错误");
            }else{
               session.setAttribute("employee",userDO);
                return success(userDO);
            }
        } catch (Exception e) {
            return fail(e);
        }
    }
}
