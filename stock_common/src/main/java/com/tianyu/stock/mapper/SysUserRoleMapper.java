package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.SysUserRole;

/**
* @author duiliang
* @description 针对表【sys_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.SysUserRole
*/
public interface SysUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

}
