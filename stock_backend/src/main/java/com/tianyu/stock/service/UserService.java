package com.tianyu.stock.service;

import com.tianyu.stock.pojo.entity.SysUser;

/**
 * UserService
 */

public interface UserService {
    SysUser findUserByName(String username);
}
