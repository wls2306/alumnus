package com.bcu.alumnus.controller;


import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.Star;
import com.bcu.alumnus.service.StarService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "校园明星模块")
@RequestMapping("/api/star")
public class StarController {

    @Resource
    private StarService starService;

    @PostMapping("/")
    @ApiOperation(value = "新增校园明星")
    @UseToken(level = 2)
    public Message insertStar(Star star) {
        return starService.insertStar(star);
    }

    @GetMapping("/")
    @ApiOperation(value = "小程序获取明星")
    public Message getStarForAlumnus(String partId) {
        return starService.getStarForAlumnus(partId);
    }

    @GetMapping("/")
    @ApiOperation(value = "发布明星根据编号(审核)")
    @UseToken(level = 4)
    public Message publishStarByStarId(String starId) {
        return starService.publishStarByStarId(starId);
    }

    @PostMapping("/")
    @ApiOperation(value = "根据编号删除校园明星")
    @UseToken(level = 4)
    public Message deleteStarByStarId(String starId) {
        return starService.deleteStarByStarId(starId);
    }

    @GetMapping("/")
    @ApiOperation(value = "查找所有状态为可见的校园明星")
    public Message getAllStar(String starStatus) {
        return starService.getAllStar(starStatus);
    }

    @GetMapping("/")
    @ApiOperation(value = "根据明星编号获取校园明星")
    public Message getStarByStarId(String starId) {
        return starService.getStarByStarId(starId);
    }

    @GetMapping("/")
    @ApiOperation(value = "根据用户编号获取校园明星")
    public Message getStarByUserId(String userId) {
        return starService.getStarByUserId(userId);
    }

    @GetMapping("/")
    @ApiOperation(value = "根据学部编号获取校园明星")
    public Message getStarByStarPartId(String starPartId) {
        return starService.getStarByStarPartId(starPartId);
    }

    @GetMapping("/")
    @ApiOperation(value = "根据可见状态获取校园明星")
    public Message getStarByStarStatus(String starStatus) {
        return starService.getStarByStarStatus(starStatus);
    }

    @PostMapping("/")
    @ApiOperation(value = "根据校园明星编号更新点赞数量")
    public Message updateStarLikeCountByStarId(String starLikeCount, String starId) {
        return starService.updateStarLikeCountByStarId(starLikeCount, starId);
    }

    @GetMapping("/")
    @ApiOperation(value = "根据主人公编号获取校园明星")
    public Message getStarByStarUserId(String starUserId) {
        return starService.getStarByStarUserId(starUserId);
    }

}
