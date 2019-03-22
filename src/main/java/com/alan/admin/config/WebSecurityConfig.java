package com.alan.admin.config;

import com.alan.admin.CustomAuthenticationProvider;
import com.alan.admin.filter.JWTAuthenticationFilter;
import com.alan.admin.filter.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 加入了spring-security,所有的路由都需要身份验证。
 * 我们将引入一个安全设置类WebSecurityConfig,这个类需要从WebSecurityConfigurerAdapter类继承。
 *
 * @author yinxing
 * @date 2019/3/21
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置 HTTP 验证规则
     * <p>
     * 在上面的安全设置类中，我们设置所有人都能访问/和POST方式访问/login，其他的任何路由都需要进行认证。
     * 然后将所有访问/login的请求，都交给JWTLoginFilter过滤器来处理。
     * 稍后我们会创建这个过滤器和其他这里需要的JWTAuthenticationFilter和CustomAuthenticationProvider两个类。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨域 csrf 验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 添加权限检测
                .antMatchers("/hello").hasAuthority("AUTH_WRITE")
                // 角色检测
                .antMatchers("/world").hasRole("ADMIN")
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 初始化过滤组件: JWTLoginFilter 和 JWTAuthenticationFilter
                // 添加一个过滤器 ：所有访问 /login 的请求交给 JWTLoginFilter 来处理， 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 注册验证组件
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
