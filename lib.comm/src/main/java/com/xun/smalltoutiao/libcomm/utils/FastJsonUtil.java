package com.xun.smalltoutiao.libcomm.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by xunwang on 2017/11/20.
 */

public class FastJsonUtil {

    /**
     * 将obj生成为JSON String数据
     */
    public static String createJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将jsonStr解析为JSON数据
     */
    public static <T>T parseJson(String jsonStr, Class<T> cls) {
        return JSON.parseObject(jsonStr, cls);
    }
}
