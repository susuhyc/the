package com.susuhyc.userinfo.model;

/**
 * Created by Administrator on 2017/10/16.
 */
public class UserInfoVO extends UserInfo {
    private String roleName;
    private String roleId;
    private String roleKey;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
}
