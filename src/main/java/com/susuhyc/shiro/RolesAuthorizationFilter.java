package com.susuhyc.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * roles多个参数时，修改过滤方式为“or”
 * @author LISS
 * @email liss@staff.chinabyte.com
 * @date 2018/12/03 14:15
 * @since 4.0
 */
public class RolesAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] rolesArray = (String[]) o;

        if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问
            return true;
        }
        //听说这里可以做缓存，目前还没有测试出多次调用的情况，嗯~先这样，以后再做。
        for (String aRolesArray : rolesArray) {
            if (subject.hasRole(aRolesArray)) { //若当前用户是rolesArray中的任何一个，则有权限访问
                return true;
            }
        }

        return false;
    }
}
