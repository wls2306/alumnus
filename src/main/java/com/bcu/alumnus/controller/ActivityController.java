package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Activity;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "校园活动模块")
@RequestMapping("api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @PostMapping("/add")
    @ApiOperation(value = "添加校园活动")
    @UseToken(level = 1)
    public Message addActivity(Activity activity){
        return activityService.addActivity(activity);
    }

    @GetMapping("/serch/passActPartId/{actPartId}")
    @ApiOperation(value = "通过学部编号查找已审核的校园活动")
    public Message getActivitybyActPartId(@PathVariable("actPartId")String actPartId){
        return activityService.getActivitybyActPartId(actPartId);
    }

    @GetMapping("/serch/passActUserId/{actUserId}")
    @ApiOperation(value = "通过创建人编号查找已审核的校园活动")
    public Message getActivitybyActUserId(@PathVariable("actUserId")String actUserId){
        return activityService.getActivitybyActUserId(actUserId);
    }

    @GetMapping("/serch/passActTypeSchool")
    @ApiOperation(value = "活动类型为校级的已审核校园活动")
    public Message getActivitybyActType(){
        return activityService.getActivitybyActType();
    }

    @GetMapping("/serch/passActUserIdAndActStatus/{actUserId}and{actStatus}")
    @ApiOperation(value = "通过创建人和活动状态查找活动")
    public Message getActivitybyActUserIdAndActStatus(@PathVariable("actUserId")String actUserId,@PathVariable("actStatus")String actStuts){
        return activityService.getActivitybyActUserIdAndActStatus(actUserId,actStuts);
    }

    @GetMapping("/serch/passActPartIdAll/{actPartId}")
    @ApiOperation(value = "通过学部编号查找所有校园活动")
    public Message getAllActivitybyActPartId(@PathVariable("actPartId")String actPartId){
        return activityService.getAllActivitybyActPartId(actPartId);
    }

}
