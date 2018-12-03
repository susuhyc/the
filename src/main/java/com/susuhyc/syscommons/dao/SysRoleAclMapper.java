package com.susuhyc.syscommons.dao;

import com.susuhyc.syscommons.model.SysRoleAcl;
import org.springframework.stereotype.Repository;


@Repository("sysRoleAclMapper")
public interface SysRoleAclMapper {
    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);

}