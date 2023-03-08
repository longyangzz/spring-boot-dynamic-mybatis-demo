package com.yhyr.mybatis.dynamic.controller;

import com.yhyr.mybatis.dynamic.domain.UserInfo;
import com.yhyr.mybatis.dynamic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * 用户信息
     */
    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    public @ResponseBody
    List<UserInfo> getUsers(){
        List<UserInfo> userInfoList = userService.getUserInfo();
        return userInfoList;
    }
}
