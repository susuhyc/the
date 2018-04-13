package com.susuhyc.userinfo.dao;

import com.susuhyc.userinfo.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/16.
 */
@Repository("userInfoDao")
public interface UserInfoDao {
    /**
     * 添加用户信息
     * @param userInfo
     */
    void saveUser(UserInfo userInfo);

    /**
     * 根据用户名和密码查询用户信息
     * @param userName
     * @param password
     * @return
     */
    UserInfo findUserByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);
}

