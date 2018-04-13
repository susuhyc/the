package com.susuhyc.util;

import com.susuhyc.userinfo.model.UserInfo;
import com.susuhyc.userinfo.service.UserInfoService;
import com.susuhyc.userinfo.service.impl.UserInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * <p>Created by LISS on 2018/04/13 10:28. </p>
 * <p>Company : TMG </p>
 * <p>Description : youke - ShiroRealm</p>
 * <p> </p>
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userService;
    private static final String SESSION_USER_KEY = "SESSION_UUID";


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       //得到session中的值
        //UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute(ShiroRealm.SESSION_USER_KEY);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");//添加用户角色
        return info;
    }

    /**
     * 认证回调函数，登录信息和用户验证信息验证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 把token转换成User对象
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String userName = userToken.getUsername();
        String password = String.valueOf(userToken.getPassword());
        //根据用户名和密码查询用户信息
        UserInfo userinfo = userService.findUserByUserNameAndPassword(userName, password);
        if(userinfo!=null){
            // 设置session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(ShiroRealm.SESSION_USER_KEY, userinfo);
            //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
            Object principal = authenticationToken.getPrincipal();
            String realmName = this.getName();
            return new SimpleAuthenticationInfo(principal, password, realmName);
        }
        return null;
    }
}
