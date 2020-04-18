package com.bcu.alumnus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${spring.profiles.active}")
    private String env;

    @Value("${gen.file-path}")
    private String path;


    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("【欢迎使用 北城校友会 后台服务】");
        logger.info("【项目负责人：李炎昊  联系电话：18501994241】");



        switch (env){
            case "dev":
                logger.info("【欢迎您对北城校友会项目进行开发】");
                logger.info("当前项目运行环境为 【开发环境】");
                logger.info("swagger已启用，可访问 http://localhost:8080/alumnus/doc.html");
                break;

            case "test":
                logger.info("当前项目运行环境为 【测试环境】");
                logger.info("swagger已启用，可访问 http://localhost:8080/alumnus/doc.html");
                break;

            case "prod":
                logger.info("当前项目运行环境为 【生产环境】");
                logger.info("swagger已禁用");
                logger.info("【如需后期维护服务，请联系移动应用开发工作室】");
                break;
        }

        logger.info("当前项目的资源文件夹为：{}",path);
        File main = new File(path);
        if (!main.exists()) {
            main.mkdirs();
            logger.info("主目录不存在，创建文件夹");
        }
        String starPath=path+"/star/";
        String newsPath=path+"/news/";
        String jobPath=path+"/job/";
        String activityPath=path+"/activity/";
        File starPathDir = new File(starPath);
        File newsPathDir = new File(newsPath);
        File jobPathDir=new File(jobPath);
        File activityPathDir=new File(activityPath);
        if (!starPathDir.exists()) {
            starPathDir.mkdirs();
            logger.info("校园之星目录不存在，创建文件夹");
        }

        if (!newsPathDir.exists()) {
            newsPathDir.mkdirs();
            logger.info("校园新闻目录不存在，创建文件夹");
        }

        if (!jobPathDir.exists()) {
            jobPathDir.mkdirs();
            logger.info("岗位内推目录不存在，创建文件夹");
        }

        if (!activityPathDir.exists()) {
            activityPathDir.mkdirs();
            logger.info("校园活动目录不存在，创建文件夹");
        }


        logger.info("【好消息】 项目启动完成，资源目录正确");




    }
}
