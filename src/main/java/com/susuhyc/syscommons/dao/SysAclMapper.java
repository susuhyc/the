package com.susuhyc.syscommons.dao;

import com.susuhyc.syscommons.model.SysAcl;
import com.susuhyc.syscommons.model.SysAclVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysAclMapper")
public interface SysAclMapper {
    int deleteByPrimaryKey(Long aclId);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Long aclId);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    List<SysAclVO> findAclAndRoles();
}