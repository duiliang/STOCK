package com.tianyu.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.tianyu.stock.mapper.SysUserMapper;
import com.tianyu.stock.pojo.entity.SysUser;
import com.tianyu.stock.service.UserService;
import com.tianyu.stock.utils.IdWorker;
import com.tianyu.stock.vo.req.LoginReqVo;
import com.tianyu.stock.vo.resp.LoginRespVo;
import com.tianyu.stock.vo.resp.R;
import com.tianyu.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return SysUser 用户信息(object)
     */
    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.findUserInfoByUserName(username);
    }

    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        // is legal?
        if (vo == null || StringUtils.isAnyBlank(vo.getUsername(), vo.getPassword(), vo.getCode())) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        //get user decode password
        SysUser dbUser = sysUserMapper.findUserInfoByUserName(vo.getUsername());
        if (dbUser==null) {
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        //compare password
        if (!passwordEncoder.matches(vo.getPassword(), dbUser.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //response
        LoginRespVo respVo = new LoginRespVo();
        BeanUtils.copyProperties(dbUser, respVo);
        return R.ok(respVo);
    }

    @Override
    public R<Map> getCaptchaCode() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 10);
        String code = lineCaptcha.getCode();
        String imageData = lineCaptcha.getImageBase64();
        String sessionID = String.valueOf(idWorker.nextId());
        log.info("sessionID:{}  code:{}", sessionID, code);
        redisTemplate.opsForValue().set("CK:"+sessionID, code, 5, TimeUnit.MINUTES);
        Map<String,String> data = new HashMap<>();
        data.put("image", imageData);
        data.put("sessionID", sessionID);
        return R.ok(data);
    }
}
