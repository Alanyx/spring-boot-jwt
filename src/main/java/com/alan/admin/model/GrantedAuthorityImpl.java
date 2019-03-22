package com.alan.admin.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * 基类:权限类型，负责存储权限和角色
 *
 * 在Spring Security中，对于GrantedAuthority接口实现类来说是不区分是Role还是Authority，
 * 二者区别就是如果是hasAuthority判断，就是判断整个字符串，判断hasRole时，系统自动加上ROLE_到判断的Role字符串上，
 * 也就是说hasRole("CREATE")和hasAuthority('ROLE_CREATE')是相同的。利用这些可以搭建完整的RBAC体系。
 *
 * @author yinxing
 * @date 2019/3/21
 */

public class GrantedAuthorityImpl implements GrantedAuthority {

    /**
     * 权限
     */
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
