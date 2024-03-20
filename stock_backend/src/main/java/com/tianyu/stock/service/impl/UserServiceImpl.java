package com.tianyu.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.tianyu.stock.constant.StockConstant;
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

import java.awt.*;
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
        if (vo == null || StringUtils.isAnyBlank(vo.getUsername(), vo.getPassword())) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        if (StringUtils.isBlank(vo.getCode()) || StringUtils.isBlank(vo.getSessionId())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }
        //redis OK?
        String redisCode =(String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + vo.getSessionId());
        if (StringUtils.isBlank(redisCode)) {
            return R.error(ResponseCode.CHECK_CODE_EXPIRED);
        }
        if (!redisCode.equalsIgnoreCase(vo.getCode())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
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
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        lineCaptcha.setBackground(Color.WHITE);
        String code = lineCaptcha.getCode();
        String imageData = lineCaptcha.getImageBase64();
        String sessionID = String.valueOf(idWorker.nextId());
        log.info("sessionID:{}  code:{} image:{}", sessionID, code,imageData);
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX+sessionID, code, 5, TimeUnit.MINUTES);
        Map<String,String> data = new HashMap<>();
        data.put("imageData", imageData);
        data.put("sessionId", sessionID);
        return R.ok(data);
    }
}
