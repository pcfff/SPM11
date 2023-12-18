package com.example.teachingplatform.util;


import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一数据返回格式
 */
public class AjaxResult {

    private Integer code;
    private String message;
    private Boolean success;
    private Object data = new HashMap<String, Object>();

    private AjaxResult(){}

    public static AjaxResult ok(){
        AjaxResult result = new AjaxResult();
        result.setCode(20000);
        result.setMessage("成功");
        result.setSuccess(true);
        return result;
    }

    public static AjaxResult error(){
        AjaxResult result = new AjaxResult();
        result.setCode(20001);
        result.setMessage("失败");
        result.setSuccess(false);
        return result;
    }

    public static AjaxResult auto(Boolean success){
        return success ? ok() : error();
    }

    public AjaxResult code(Integer code){
        this.code = code;
        return this;
    }

    public AjaxResult message(String message){
        this.message = message;
        return this;
    }

    public AjaxResult success(Boolean success){
        this.success = success;
        return this;
    }

    public AjaxResult data(String key, Object value){
        if (data instanceof Map){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) data;
            map.put(key, value);
        }else {
            throw new RuntimeException("Result.data数据类型错误");
        }
        return this;
    }

    public AjaxResult data(Object value){
        data = value;
        return this;
    }

    public void write(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(this));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
