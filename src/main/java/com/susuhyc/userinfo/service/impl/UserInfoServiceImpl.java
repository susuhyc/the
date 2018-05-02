package com.susuhyc.userinfo.service.impl;

import com.susuhyc.userinfo.dao.UserInfoDao;
import com.susuhyc.userinfo.model.UserInfo;
import org.springframework.stereotype.Service;
import com.susuhyc.userinfo.service.UserInfoService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/10/16.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;


    @Override
    public void saveUser(UserInfo userInfo) {
         userInfoDao.saveUser(userInfo);
    }

    @Override
    public UserInfo findUserByUserNameAndPassword(String userName, String password) {
        return userInfoDao.findUserByUserNameAndPassword(userName,password);
    }
}
