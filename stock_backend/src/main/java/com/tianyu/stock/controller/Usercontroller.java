package com.tianyu.stock.controller;

import com.tianyu.stock.pojo.entity.SysUser;
import com.tianyu.stock.service.UserService;
import com.tianyu.stock.vo.req.LoginReqVo;
import com.tianyu.stock.vo.resp.LoginRespVo;
import com.tianyu.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        return userService.login(vo);
    }

    @GetMapping("/captcha")
    public R<Map> getCaptcha(){
        return userService.getCaptchaCode();
    }

}
