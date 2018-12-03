package com.susuhyc.syscommons.dao;

import com.susuhyc.syscommons.model.SysUserRoleKey;
import org.springframework.stereotype.Repository;

@Repository("sysUserRoleMapper")
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);
}