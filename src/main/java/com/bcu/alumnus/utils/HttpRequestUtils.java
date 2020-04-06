package com.bcu.alumnus.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
* @Author: Wls
* @Date: 11:38 2020/4/6
* @Description: 网络请求工具类
*/
public class HttpRequestUtils {

    /**
    * @Author: Wls
    * @Date: 11:38 2020/4/6
    * @Description: 表单形式发送POST请求
    */
    public static String doPost(String url,Map param){
        return HttpRequest.post(url)
                .form(param)
                .execute().body();
    }


    /**
    * @Author: Wls
    * @Date: 11:38 2020/4/6
    * @Description: 表单形式发送GET请求
    */
    public static String doGet(String url,Map param){
        return HttpRequest.get(url)
                .form(param)
                .execute().body();
    }

    /**
    * @Author: Wls
    * @Date: 11:38 2020/4/6
    * @Description: RESTFUL风格POST请求
    */
    public static String doPostRestful(String url,Map param){
        return HttpRequest.post(url)
                .body(JSONUtil.toJsonStr(param))
                .execute().body();
    }

    /**
    * @Author: Wls
    * @Date: 11:39 2020/4/6
    * @Description: RESTFUL风格GET请求
    */
    public static String doGetRestful(String url,Map param){
        return HttpRequest.get(url)
                .body(JSONUtil.toJsonStr(param))
                .execute().body();
    }

}
