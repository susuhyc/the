package com.susuhyc.userinfo.dao;

import com.susuhyc.userinfo.model.UserInfo;
import com.susuhyc.userinfo.model.UserInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询用户角色
     * @param userInfo
     * @return
     */
    List<UserInfoVO> findUserRole(@Param("param")UserInfo userInfo);
}

