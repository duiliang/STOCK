package com.tianyu.stock.controller;

import com.tianyu.stock.pojo.entity.SysUser;
import com.tianyu.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class Usercontroller {
    @Autowired
    private UserService userService;
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return SysUser 用户信息(object)
     */
    @GetMapping("/user/{username}")
    public SysUser getUserInfo(@PathVariable("username") String username) {
        return userService.findUserByName(username);
    }
}
