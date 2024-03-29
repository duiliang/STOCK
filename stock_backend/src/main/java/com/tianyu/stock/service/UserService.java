package com.tianyu.stock.service;

import com.tianyu.stock.pojo.entity.SysUser;
import com.tianyu.stock.vo.req.LoginReqVo;
import com.tianyu.stock.vo.resp.LoginRespVo;
import com.tianyu.stock.vo.resp.R;

import java.util.Map;

/**
 * UserService
 */

public interface UserService {
    SysUser findUserByName(String username);

    R<LoginRespVo> login(LoginReqVo vo);

    R<Map> getCaptchaCode();
}
