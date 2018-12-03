package com.susuhyc.shiro;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 修改角色权限后实时刷新FilterChainDefinition
 * @author LISS
 * @email liss@staff.chinabyte.com
 * @date 2018/12/03 16:03
 * @since 4.0
 */
@Component
public class RefreshFilterChainDefinition {

    @Autowired
    private FilterChainDefinitionFactoryBean filterChainDefinitionFactoryBean;

    public void reloadFilterChains() {
        synchronized (filterChainDefinitionFactoryBean) {   //强制同步，控制线程安全
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) filterChainDefinitionFactoryBean.getObject();

                PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
                        .getFilterChainResolver();
                // 过滤管理器
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
                // 清除权限配置
                manager.getFilterChains().clear();
                filterChainDefinitionFactoryBean.getFilterChainDefinitionMap().clear();
                // 重新设置权限
                filterChainDefinitionFactoryBean.setFilterChainDefinitions(FilterChainDefinitionFactoryBean.filterChainDefinition);//传入配置中的filterchains

                Map<String, String> chains = filterChainDefinitionFactoryBean.getFilterChainDefinitionMap();

                for (String key : chains.keySet()) {//keySet获取map集合key的集合  然后在遍历key即可
                    String value = chains.get(key).toString();//
                    System.out.println("key:" + key + " value:" + value);
                }

                //重新生成过滤链
                if (!CollectionUtils.isEmpty(chains)) {
                    chains.forEach((url, definitionChains) -> {
                        manager.createChain(url, definitionChains.trim().replace(" ", ""));
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
