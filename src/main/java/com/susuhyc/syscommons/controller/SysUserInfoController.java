package com.susuhyc.syscommons.controller;

import com.susuhyc.shiro.RefreshFilterChainDefinition;
import com.susuhyc.syscommons.model.SysUser;
import com.susuhyc.syscommons.service.SysUserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/16.
 */
@RestController
@RequestMapping("user")
public class SysUserInfoController {

    @Resource
    private SysUserInfoService sysUserInfoService;

    @Resource
    private RefreshFilterChainDefinition refreshFilterChainDefinition;

    /**
     * 登陆
     * @param user
     * @return
     */
    @RequestMapping("/dologin")
    public ModelMap dologin(SysUser user){
        ModelMap model = new ModelMap();
        boolean info = sysUserInfoService.loginIn(user);
        if (info){
            model.put("successMsg", "登陆成功！");//返回到页面说夹带的参数
            model.put("name", user.getUserName());
        }
        return model;
    }

    /**
     * 退出登陆
     * @return
     */
    @RequestMapping("/logout")
    public Map logout(){
        Map<String,Object> map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
          subject.logout();
        }
        map.put("msg","退出成功！");
        return map;
    }


    /**
     * 根据角色权限判断是否显示
     * @return
     */
    @RequestMapping("/admin")
    public Map admin(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("msg","权限验证成功！");
        return map;
    }
}
