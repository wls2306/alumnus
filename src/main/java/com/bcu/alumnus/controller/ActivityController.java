package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Activity;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "校园活动模块")
@RequestMapping("api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @PostMapping("/add")
    @ApiOperation(value = "添加校园活动，初始状态为0，待审核,活动创建时间为当前时间 @UseToken(level=1)")
    @UseToken(level = 1)
    public Message<Activity> addActivity(Activity activity){
        return activityService.addActivity(activity);
    }

    @GetMapping("/pass/part/{actPartId}")
    @ApiOperation(value = "通过学部编号查找已审核的校园活动")
    public Message<List<Activity>> getActivitybyActPartId(@PathVariable("actPartId")String actPartId){
        return activityService.getActivityByActPartId(actPartId);
    }

    @GetMapping("/pass/user/{actUserId}")
    @ApiOperation(value = "通过创建人编号查找已审核的校园活动")
    public Message<List<Activity>> getActivitybyActUserId(@PathVariable("actUserId")String actUserId){
        return activityService.getActivityByActUserId(actUserId);
    }


    @GetMapping("/pass/list/{actPartId}")
    @ApiOperation(value = "【小程序活动列表】通过学部编号查找已审核的校园活动 以及 活动类型为校级的已审核校园活动")
    public Message<List<Activity>> getActivitybyActType(@PathVariable("actPartId")String actPartId){
        return activityService.getActivityByActPartIdAndActType(actPartId);
    }

    @GetMapping("/user/status/{actUserId}/{actStatus}")
    @ApiOperation(value = "通过创建人和活动状态查找活动")
    public Message<List<Activity>> getActivitybyActUserIdAndActStatus(@PathVariable("actUserId")String actUserId,@PathVariable("actStatus")String actStuts){
        return activityService.getActivityByActUserIdAndActStatus(actUserId,actStuts);
    }

    @GetMapping("/part/{actPartId}")
    @ApiOperation(value = "通过学部编号查找所有校园活动")
    public Message<List<Activity>> getAllActivitybyActPartId(@PathVariable("actPartId")String actPartId){
        return activityService.getAllActivityByActPartId(actPartId);
    }

    @GetMapping("/all")
    @ApiOperation(value = "查找所有活动")
    public Message<List<Activity>> getAllActivity(){
        return activityService.getAllActivity();
    }

    @GetMapping("/status/{actStatus}")
    @ApiOperation(value = "通过活动状态查找活动")
    public Message<List<Activity>> getActivitybyActStatus(@PathVariable("actStatus")String actStatus){
        return activityService.getActivityByActStatus(actStatus);
    }

    @GetMapping("/type/{actType}")
    @ApiOperation(value = "通过活动类型查找活动")
    public Message<List<Activity>> getActivityByActType(@PathVariable("actType")String actType){
        return activityService.getActivityByActType(actType);
    }

    @GetMapping("/id/{actId}")
    @ApiOperation(value = "通过活动Id查找活动")
    public Message<Activity> getActivityByActId(@PathVariable("actId")int actId){
        return activityService.getActivityByActId(actId);
    }

    @GetMapping("/verify/{actId}")
    @ApiOperation(value = "审核活动（根据活动编号将活动状态改为1）@UseToken(level=3)")
    @UseToken(level = 3)
    public Message<?> auditActivityByActId(@PathVariable("actId")String actId){
        return activityService.updataActivityByActId("1",actId);
    }

    @PutMapping("/status/{actId}/{actStatus}")
    @ApiOperation(value = "修改活动状态")
    public Message<?> auditActivityByActId(@PathVariable("actStatus")String actStatus,@PathVariable("actId")String actId){
        return activityService.updataActivityByActId(actStatus,actId);
    }
}
