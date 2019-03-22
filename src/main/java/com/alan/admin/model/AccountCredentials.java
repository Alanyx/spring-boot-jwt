package com.alan.admin.model;

/**
 * 基类:负责存储用户名密码
 *
 * @author yinxing
 * @date 2019/3/21
 */

public class AccountCredentials {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
