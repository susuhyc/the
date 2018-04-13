package com.susuhyc.userinfo.service;

import com.susuhyc.userinfo.model.UserInfo;

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

}
