package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.config.GlobalConfig;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.utils.GlobalFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
@Api(tags = "全局文件上传模块")
public class GlobalFileController {

    @Resource
    private GlobalConfig config;


    /**
    * @Author: Wls
    * @Date: 17:50 2020/4/7
    * @Description: 全局文件上传接口
    */
    @PostMapping("/")
    @ApiOperation(value = "文件上传，返回文件名数组")
    @UseToken(level = 1)
    public Message fileUpload(MultipartFile[] multipartFile) throws IOException {
        StringBuilder filesNames = new StringBuilder();
        if (multipartFile.length>0) {
//            for (MultipartFile file : multipartFile) {
//                String format= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
//                System.out.println(format);
//                String fileName=UUID.randomUUID().toString()+"."+format;
//                String dir=config.globalFilePath+"/temp/";
//                File f=new File(dir);
//                if (!f.exists()) {
//                    f.mkdirs();
//                }
//                File saveFile = new File(dir+fileName);
//                file.transferTo(saveFile);
//                filesNames.append(fileName);
//                filesNames.append(";");
//            }
//            return Message.success(null).add(filesNames.toString());
            String rs= GlobalFileUtils.saveFileToTempDir(config.globalFilePath,multipartFile);
            return Message.success(null).add(rs);
        }
        return Message.fail("没有读取到您的上传文件信息");
    }
}
