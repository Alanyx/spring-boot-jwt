package com.alan.admin;

import com.alan.admin.common.JSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinxing
 * @date 2019/3/21
 */

@RestController
public class UserController {

    // 根目录映射 Get访问方式 直接返回一个字符串
    @RequestMapping("/")
    public Map<String, String> hello1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("content", "hello freewolf~");
        return map;
    }

    // 路由映射到/users
    @PostMapping(value = "/users", produces = "application/json;charset=UTF-8")
    public String usersList() {

        ArrayList<String> users = new ArrayList<String>() {{
            add("jack");
            add("tom");
            add("jerry");
        }};

        return JSONResult.fillResultString(0, "", users);
    }

    @PostMapping(value = "/hello", produces = "application/json;charset=UTF-8")
    public String hello() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("hello");
        }};
        return JSONResult.fillResultString(0, "", users);
    }

    @PostMapping(value = "/world", produces = "application/json;charset=UTF-8")
    public String world() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("world");
        }};
        return JSONResult.fillResultString(0, "", users);
    }
}
