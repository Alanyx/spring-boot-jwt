# spring-boot-jwt

## 概述
spring boot + spring security + jwt 安全认证 


## 测试说明
### 1.运行项目
先运行整个项目，这里介绍下过程：

- 先程序启动 - main函数

- 注册验证组件 - WebSecurityConfig 类 configure(AuthenticationManagerBuilder auth)方法，这里我们注册了自定义验证组件

- 设置验证规则 - WebSecurityConfig 类 configure(HttpSecurity http)方法，这里设置了各种路由访问规则

- 初始化过滤组件 - JWTLoginFilter 和 JWTAuthenticationFilter 类会初始化

首先测试获取Token，这里使用CURL命令行工具来测试。

```
curl -H "Content-Type: application/json" -X POST -d '{"username":"admin","password":"123456"}' http://127.0.0.1:8080/login
```

结果：

```
{
	"result": "eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4sQVVUSF9XUklURSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNTUzNjA1MDAxfQ.-9ja4lSkkReVoRRYHI37NggDJ9XkK-N9Lry13ykbN-RUxZhiMvlZQoNg6_Sd9X6uhFJBEa59n_WGq_yYT0n41Q",
	"message": "",
	"status": 0
}
```

整个过程如下：

- 拿到传入JSON，解析用户名密码 - JWTLoginFilter 类 attemptAuthentication 方法

- 自定义身份认证验证组件，进行身份认证 - CustomAuthenticationProvider 类 authenticate 方法

- 盐成功 - JWTLoginFilter 类 successfulAuthentication 方法

- 生成JWT - TokenAuthenticationService 类 addAuthentication方法

### 2.再测试一个访问资源

```
curl -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4sQVVUSF9XUklURSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNDkzNzgyMjQwfQ.HNfV1CU2CdAnBTH682C5-KOfr2P71xr9PYLaLpDVhOw8KWWSJ0lBo0BCq4LoNwsK_Y3-W3avgbJb0jW9FNYDRQ" http://127.0.0.1:8080/users
```


## 参考
https://segmentfault.com/a/1190000009231329