package com.tianyu.stock.service.impl;

import com.tianyu.stock.mapper.SysUserMapper;
import com.tianyu.stock.pojo.entity.SysUser;
import com.tianyu.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return SysUser 用户信息(object)
     */
    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.findUserInfoByUserName(username);
    }
}
