package com.example.teachingplatform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.util.List;

public class RequestUtil {

    public static<T> T parseJson(HttpServletRequest request, Class<T> clazz)  {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonString = stringBuilder.toString();
            // 使用 Fastjson 将 JSON 字符串转换成 Java 对象
            JSONObject jsonObject = JSON.parseObject(jsonString);
            return jsonObject.toJavaObject(clazz);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static<T> List<T> parseList(HttpServletRequest request, Class<T> clazz)  {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonString = stringBuilder.toString();
            // 使用 Fastjson 将 JSON 字符串转换成 Java 对象
            JSONArray jsonArray = JSON.parseArray(jsonString);
            return jsonArray.toJavaList(clazz);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getUserId(HttpServletRequest request){
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (token == null || "".equals(token)) {
            return null;
        }
        try {
            return JwtTokenUtils.getUserId(token);
        } catch (Exception e) {
            return null;
        }
    }

}
