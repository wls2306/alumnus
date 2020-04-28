package com.bcu.alumnus.controller;

import com.bcu.alumnus.entity.Apply;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.service.ApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "活动报名模块")
@RequestMapping("/api/apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    @PostMapping("/")
    @ApiOperation(value = "添加报名信息，并根据活动的录取形式，生成报名状态（若报名不须审核则直接录取状态）")
    public Message<Apply> insertApply(Apply apply){
        return applyService.insertApply(apply);
    }

    @GetMapping("/search/status/{ApplyId}")
    @ApiOperation(value = "根据活动编号查询已录取的报名信息")
    public Message<List<Apply>> getApplyByApplyIdAndApplyStatus(@PathVariable("ApplyId")int ApplyId){
        return applyService.getApplyByApplyIdAndApplyStatus(ApplyId);
    }

    @GetMapping("/search/activityId/{activityId}")
    @ApiOperation(value = "根据活动编号查询所有的报名信息")
    public Message<List<Apply>> getApplyByActivityId(@PathVariable("activityId")int activityId){
        return applyService.getApplyByActivityId(activityId);
    }

    @GetMapping("/search/applyId/{applyId}")
    @ApiOperation(value = "根据报名编号查询所有的报名详细信息")
    public Message<Apply> getApplyByApplyId(@PathVariable("applyId")int applyId){
        return applyService.getApplyByApplyId(applyId);
    }

    @GetMapping("/search/ApplyUserId/{ApplyUserId}")
    @ApiOperation(value = "根据用户编号查询所有的报名信息")
    public Message<List<Apply>> getApplyByApplyUserId(@PathVariable("ApplyUserId")String ApplyUserId){
        return applyService.getApplyByApplyUserId(ApplyUserId);
    }

    @GetMapping("/search/userId/status/{ApplyUserId}/{ApplyStatus}")
    @ApiOperation(value = "通过用户编号查询和录取状态查询报名信息")
    public Message getApplyByApplyUserIdAndApplyStatus(@PathVariable("ApplyUserId")String ApplyUserId,@PathVariable("ApplyStatus")String ApplyStatus){
        return applyService.getApplyByApplyUserIdAndApplyStatus(ApplyUserId,ApplyStatus);
    }

    @GetMapping("/count/ApplyId/{ApplyId}")
    @ApiOperation(value = "通过活动编号统计报名数量")
    public Message countApplyByApplyId(@PathVariable("ApplyId")int ApplyId){
        return applyService.countApplyByApplyId(ApplyId);
    }

    @GetMapping("/count/ApplyId/Status/{ApplyId}")
    @ApiOperation(value = "通过活动编号统计已录取的报名数量")
    public Message countApplyByApplyIdAndApplyStatus(@PathVariable("ApplyId")int ApplyId){
        return applyService.countApplyByApplyIdAndApplyStatus(ApplyId);
    }

    @GetMapping("/upData/{ApplyId}/{ApplyUserId}")
    @ApiOperation(value = "通过活动编号、用户编号修改录取状态")
    public Message UpdateApplyStatusByApplyIdAndApplyUserId(@PathVariable("ApplyId")int ApplyId,@PathVariable("ApplyUserId")String AppplyUserId){
        return applyService.UpdateApplyStatusByApplyIdAndApplyUserId(ApplyId,AppplyUserId);
    }
}
