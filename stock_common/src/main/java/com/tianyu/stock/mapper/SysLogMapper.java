package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.SysLog;

/**
* @author duiliang
* @description 针对表【sys_log(系统日志)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.SysLog
*/
public interface SysLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

}
