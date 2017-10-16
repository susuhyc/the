package com.susuhyc.userinfo.dao;

import com.susuhyc.userinfo.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/16.
 */
@Repository("userInfoDao")
public interface UserInfoDao {
    void saveUser(UserInfo userInfo);
}

