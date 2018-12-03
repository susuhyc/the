package com.susuhyc.syscommons.dao;

import com.susuhyc.syscommons.model.SysUser;
import com.susuhyc.syscommons.model.SysUserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysUserMapper")
public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名和密码查询用户信息
     * @param userName
     * @param password
     * @return
     */
    SysUser findUserByUserNameAndPassword(@Param("userName")String userName, @Param("password")String password);


    /**
     * 查询用户角色
     * @param userInfo
     * @return
     */
    List<SysUserVO> findUserRole(@Param("param")SysUser userInfo);
}