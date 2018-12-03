package com.susuhyc.syscommons.model;

public class SysRoleAcl {
    private String roleId;

    private String aclId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId == null ? null : aclId.trim();
    }

    @Override
    public String toString() {
        return "SysRoleAcl{" +
                "roleId='" + roleId + '\'' +
                ", aclId='" + aclId + '\'' +
                '}';
    }
}