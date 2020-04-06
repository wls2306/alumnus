package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.UserInfo;
import com.bcu.alumnus.exception.TokenNotExistsExpectation;
import com.bcu.alumnus.service.UserInfoService;
import com.bcu.alumnus.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户信息模块")
@RequestMapping("/api/ui/")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "根据用户编号查询用户详细信息")
    @GetMapping("/")
    public Message getUserDetailInfoByUserId(String token,String userId)throws Exception
    {
        /*
        * 查询用户
        * 进行权限判断
        * token为空，报错
        * 如果是校友，只能查自己的信息
        * 如果是管理员，想查谁查谁
        * */
        if (token == null) {
            throw new TokenNotExistsExpectation();
        }
        Claims claims = JwtUtil.resolveToken(token);
        Integer userType = Integer.valueOf((String)claims.get("userType"));
        if (userType>1)
            return userInfoService.getUserInfoByUserId(userId);
        else
            return userInfoService.getUserInfoByUserId((String) claims.get("userId"));
    }

    @ApiOperation(value = "[需要token]根据用户班级名称查询用户信息")
    @GetMapping("/class")
    @UseToken(level = 2)
    public Message getUserDetailInfoByUserClass(String userClassName){
        return userInfoService.getUserInfoByClassName(userClassName);
    }


    @ApiOperation(value = "[需要token]根据用户学部名称查询用户信息")
    @GetMapping("/part")
    @UseToken(level = 3)
    public Message getUserDetailInfoByUserPart(String userPartName){
        return userInfoService.getUserInfoByPartName(userPartName);
    }

    @ApiOperation(value = "[需要token]小程序端更新用户通信信息")
    @PutMapping("/alumnus")
    @UseToken(level = 1)
    public Message updateUserInfoByUserId(String userId,String userPhone,String userEmail){
        return userInfoService.updateUserInfoByUserIdForAlumnus(userId, userPhone, userEmail);
    }

    @ApiOperation(value = "[需要token]管理端端更新用户全部详细信息")
    @PutMapping("/")
    @UseToken(level = 2)
    public Message updateUserInfoByUserId(String userId, UserInfo userInfo){
        userInfo.setUserId(userId);
        return userInfoService.updateUserInfoByUserIdForAdmin(userInfo);
    }




}
