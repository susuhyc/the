package com.susuhyc.userinfo.service;

import com.susuhyc.userinfo.model.UserInfo;
import com.susuhyc.userinfo.model.UserInfoVO;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface UserInfoService {
    void saveUser(UserInfo userInfo);

    /**
     * 根据用户名和密码查询用户信息
     * @param userName
     * @param password
     * @return
     */
    UserInfo findUserByUserNameAndPassword(String userName,String password);


    /**
     * 查询用户角色
     * @param userInfo
     * @return
     */
    List<UserInfoVO> findUserRole(UserInfo userInfo);

}
