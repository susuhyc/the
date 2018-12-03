package com.susuhyc.shiro;

import com.susuhyc.syscommons.model.SysAclVO;
import com.susuhyc.syscommons.service.SysUserInfoService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

/**
 * 处理角色权限
 * @author LISS
 * @email liss@staff.chinabyte.com
 * @date 2018/11/29 18:02
 * @since 4.0
 */
public class FilterChainDefinitionFactoryBean extends ShiroFilterFactoryBean {

    @Resource
    private SysUserInfoService sysUserInfoService;

    public static String filterChainDefinition;

    private static final String ROLES_STRING="roles[\"{0}\"]";


    @Override
    public void setFilterChainDefinitions(String definitions) {
        filterChainDefinition = definitions;
        String permiss = null;
        Ini ini = new Ini();
        ini.load(definitions);   //先载入XML中定义好的chain
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        List<SysAclVO> aclAndRoles = sysUserInfoService.findAclAndRoles();
        for (SysAclVO sysAclVO :aclAndRoles) {
            permiss = MessageFormat.format(ROLES_STRING,sysAclVO.getRoleKeys());
            section.put(sysAclVO.getUrl(),permiss);
        }
        setFilterChainDefinitionMap(section);
    }

}
