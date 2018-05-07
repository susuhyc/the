package com.susuhyc.userinfo.controller;

import com.susuhyc.userinfo.model.UserInfo;
import com.susuhyc.userinfo.service.UserInfoService;
import com.susuhyc.util.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/16.
 */
@RestController
@RequestMapping("user")
public class UserInfoControl {

    @Resource
    private UserInfoService userService;
    private static final String SESSION_USER_KEY = "SESSION_UUID";

    /**
     * 登陆
     * @param user
     * @return
     */
    @RequestMapping("/dologin")
    public ModelMap dologin(UserInfo user){
        ModelMap model = new ModelMap();
        String info = loginUser(user);
        if (!"SUCC".equals(info)) {
            model.put("failMsg", "用户不存在或密码错误！");
        }else{
            model.put("successMsg", "登陆成功！");//返回到页面说夹带的参数
            model.put("name", user.getUserName());
        }
        return model;
    }
    @RequestMapping("/logout.do")
    public ModelAndView logout(){
        Map<String,Object> map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try{
                subject.logout();
            }catch(Exception ex){
            }
        }
        return new ModelAndView("index",map);
    }


    @RequestMapping("/admin")
    public Map admin(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("xxxx","xxxxx");
        return map;
    }










    private String loginUser(UserInfo user){
        if (isRelogin(user)) return "SUCC"; // 如果已经登陆，无需重新登录
        return shiroLogin(user); // 调用shiro的登陆验证
    }

    private boolean isRelogin(UserInfo user) {
        //是否是同一个用户
        Session session = SecurityUtils.getSubject().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(SESSION_USER_KEY);
        Boolean isTheSameUser = false;
        if(userInfo!=null&&userInfo.getUserName().equals(user.getUserName())&&userInfo.getPassword().equals(user.getPassword())){
            isTheSameUser = true;
        }
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()&&isTheSameUser) {
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        //如果不是同一用户 直接退出登陆
        if(!isTheSameUser){
            SecurityUtils.getSubject().logout();
        }
        return false; // 需要重新登陆
    }


    private String shiroLogin(UserInfo user) {
        // 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);

        // shiro登陆验证
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (AuthenticationException ex) {
            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }



}
