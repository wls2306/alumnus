package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "校园活动模块")
@RequestMapping("api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    //@PostMapping("/add")
    //@ApiOperation(value = "添加校园活动")
    //@UseToken(level = 1)
    //public Message addActivity(int actId,String actPartId,String actUserId,String actTitleImg){}

}
