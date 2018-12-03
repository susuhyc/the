package com.susuhyc.syscommons.model;

public class SysUserRoleKey {
    private Long roleId;

    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysUserRoleKey{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}