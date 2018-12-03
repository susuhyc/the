package com.susuhyc.syscommons.service;

import com.susuhyc.syscommons.model.SysAclVO;
import com.susuhyc.syscommons.model.SysUser;
import com.susuhyc.syscommons.model.SysUserVO;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface SysUserInfoService {

    /**
     * 保存用户信息
     * @param userInfo
     */
    void saveUser(SysUser userInfo);

    /**
     * 根据用户名和密码查询用户信息
     * @param userName
     * @param password
     * @return
     */
    SysUser findUserByUserNameAndPassword(String userName, String password);


    /**
     * 查询用户角色
     * @param userInfo
     * @return
     */
    List<SysUserVO> findUserRole(SysUser userInfo);

    /**
     * 查询角色权限信息
     * @return
     */
    List<SysAclVO> findAclAndRoles();

    /**
     * shrio登陆
     * @param users
     * @return
     */
    boolean loginIn(SysUser users);

}
