package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.service.UserService;
import com.bcu.alumnus.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "用户模块")
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/token")
    @ApiOperation(value = "使用用户名和密码获取用户token")
    public Message userLogin(String userId, String password){
        return userService.userLoginByUserIdAndPassword(userId, password);
    }


    @GetMapping("/token")
    @ApiOperation(value = "使用小程序jsCode获取用户token")
    public Message userLogin(String jsCode){
        return userService.userLoginByJsCode(jsCode);
    }

    @PostMapping("/register")
    @ApiOperation(value = "新增管理端用户")
    @UseToken(level = 3)
    public Message managerUserRegister(String userId,String userName,String password,String partId,String type){
        return userService.insertManageUser(userId, userName, password, partId, type);
    }

    @GetMapping("/classId")
    @ApiOperation(value = "根据班级编号获取用户")
    @UseToken(level = 2)
    public Message getUserByClassId(String classId){
        return userService.getUserByClassId(classId);
    }

    @GetMapping("/partId")
    @ApiOperation(value = "根据学部编号获取用户")
    @UseToken(level = 3)
    public Message getUserByPartId(String partId)
    {
        return userService.getUserByPartId(partId);
    }








}
