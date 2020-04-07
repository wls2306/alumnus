package com.bcu.alumnus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wls
 * @Date: 15:29 2020/4/7
 * @Description: 全局配置类
 */
@Configuration
public class GlobalConfig {

    /**
     * 小程序号
     */
    @Value("${wechat.appId}")
    public String appId;

    /**
     * 小程序密钥
     */
    @Value("${wechat.secret}")
    public String appSecret;

    /**
     * 全局文件
     */
    @Value("${gen.file-path}")
    public String globalFilePath;



}
