package com.susuhyc.syscommons.dao;

import com.susuhyc.syscommons.model.SysRole;
import org.springframework.stereotype.Repository;

@Repository("sysRoleMapper")
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}