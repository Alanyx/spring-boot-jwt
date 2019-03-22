package com.alan.admin.common;


import org.json.JSONObject;

/**
 * 显示统一的JSON返回，这里建立一个JSONResult类进行
 * @author yinxing
 * @date 2019/3/21
 */

public class JSONResult {
    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};

        return jsonObject.toString();
    }
}
