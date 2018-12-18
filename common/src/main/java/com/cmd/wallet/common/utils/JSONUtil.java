package com.cmd.wallet.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

/**
 * JSON工具类
 * Created by jerry on 2018/1/8.
 */
public final class JSONUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        // 不确定的属性项上不要失败
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Map JsonToMap(String json){ return (Map) (Map) JSON.parse(json); }
    public static String MapToJson(Map m){
        return toJSONString(m);
    }

    public static String toJSONString(Object obj) {
        if(obj == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T extends Object> T parseToObject(String source, Class<T> valueType) {
        try {
            return objectMapper.readValue(source, valueType);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T extends Object> T parseToObject(String source, TypeReference valueType) {
        try {
            return objectMapper.readValue(source, valueType);
        } catch (IOException e) {
            return null;
        }
    }


}
