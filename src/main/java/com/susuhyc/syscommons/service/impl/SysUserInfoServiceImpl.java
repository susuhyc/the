package com.susuhyc.syscommons.service.impl;

import com.susuhyc.syscommons.dao.SysAclMapper;
import com.susuhyc.syscommons.dao.SysRoleAclMapper;
import com.susuhyc.syscommons.dao.SysUserMapper;
import com.susuhyc.syscommons.model.SysAclVO;
import com.susuhyc.syscommons.model.SysUser;
import com.susuhyc.syscommons.model.SysUserVO;
import com.susuhyc.syscommons.service.SysUserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
@Service("sysUserInfoService")
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysAclMapper sysAclMapper;


    private static final String SESSION_USER_KEY = "SESSION_UUID";


    @Override
    public void saveUser(SysUser userInfo) {
        sysUserMapper.insert(userInfo);
    }

    @Override
    public SysUser findUserByUserNameAndPassword(String userName, String password) {
        return sysUserMapper.findUserByUserNameAndPassword(userName, password);
    }

    @Override
    public List<SysUserVO> findUserRole(SysUser userInfo) {
        return sysUserMapper.findUserRole(userInfo);
    }

    @Override
    public List<SysAclVO> findAclAndRoles() {
        return sysAclMapper.findAclAndRoles();
    }


    public boolean loginIn(SysUser users) {
        return this.isRelogin(users) ? this.isRelogin(users) : this.shiroLogin(users) ;
    }


    /**
     * 判断是否是同一个用户，如果是就不需要重新登陆，不是就退出登陆后再重新登陆。
     * @param user
     * @return
     * true:是相同用户
     * false:不是相同用户
     */
    private boolean isRelogin(SysUser user) {
        //是否是同一个用户
        Session session = SecurityUtils.getSubject().getSession();
        SysUser userInfo = (SysUser) session.getAttribute(SESSION_USER_KEY);
        Boolean isTheSameUser = false;
        if (userInfo != null && userInfo.getUserName().equals(user.getUserName()) && userInfo.getPassword().equals(user.getPassword())) {
            isTheSameUser = true;
        }
        Subject us = SecurityUtils.getSubject();
        // 参数未改变，无需重新登录，默认为已经登录成功
        if (us.isAuthenticated() && isTheSameUser) {
            return true;
        }
        //如果不是同一用户 直接退出登陆
        if (!isTheSameUser) {
            SecurityUtils.getSubject().logout();
        }
        return false;
    }


    /**
     *shiro方式登录方法
     * @param user
     * @return
     * false 登陆失败
     * true 登陆成功
     */
    private boolean shiroLogin(SysUser user) {
        // 组装token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            throw new IllegalStateException("用户不存在或者密码错误！");
        } catch (AuthenticationException ex) {
            throw new IllegalStateException(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex.getMessage());
        }
        return true;
    }


}
