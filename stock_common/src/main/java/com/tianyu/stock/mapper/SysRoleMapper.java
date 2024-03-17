package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.SysRole;

/**
* @author duiliang
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.SysRole
*/
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

}
