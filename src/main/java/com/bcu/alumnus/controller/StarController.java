package com.bcu.alumnus.controller;


import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.Star;
import com.bcu.alumnus.service.StarService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "校园明星模块")
@RequestMapping("/api/star")
public class StarController {

    @Resource
    private StarService starService;

    @PostMapping("/add")
    @ApiOperation(value = "新增校园明星")
    @UseToken(level = 2)
    public Message insertStar(Star star) {
        return starService.insertStar(star);
    }

    @GetMapping("/visiblePart")
    @ApiOperation(value = "通过学部编号查找状态为可见的校园明星")
    public Message getStarForAlumnus(String partId) {
        return starService.getStarByStarPartIdAndAndStarStatus(partId);
    }

    @GetMapping("/pub")
    @ApiOperation(value = "发布明星根据编号(审核)")
    @UseToken(level = 4)
    public Message publishStarByStarId(String starId) {
        return starService.publishStarByStarId(starId);
    }

    @PostMapping("/delete/{starId}")
    @ApiOperation(value = "根据编号删除校园明星")
    @UseToken(level = 4)
    public Message deleteStarByStarId(@PathVariable("starId")String starId) {
        return starService.deleteStarByStarId(starId);
    }

    @GetMapping("/all")
    @ApiOperation(value = "查找所有状态为可见的校园明星")
    public Message getAllStar(String starStatus) {
        return starService.getAllStar(starStatus);
    }

    @GetMapping("/starId/{starId}")
    @ApiOperation(value = "根据明星编号获取校园明星")
    public Message getStarByStarId(@PathVariable("starId")String starId) {
        return starService.getStarByStarId(starId);
    }

   /* @GetMapping("/userId/{userId}")
    @ApiOperation(value = "根据用户编号获取校园明星")
    public Message getStarByUserId(@PathVariable("userId") String userId) {
        return starService.getStarByUserId(userId);
    }*/

    @GetMapping("/part/{starPartId}")
    @ApiOperation(value = "根据学部编号获取校园明星")
    public Message getStarByStarPartId(@PathVariable("starPartId") String starPartId) {
        return starService.getStarByStarPartId(starPartId);
    }

    @GetMapping("/status/{starStatus}")
    @ApiOperation(value = "根据可见状态获取校园明星")
    public Message getStarByStarStatus(@PathVariable("starStatus")String starStatus) {
        return starService.getStarByStarStatus(starStatus);
    }

    @PostMapping("/likeCount/{starLikeCount}/{starId}")
    @ApiOperation(value = "根据校园明星编号更新点赞数量")
    public Message updateStarLikeCountByStarId(@PathVariable("starLikeCount")String starLikeCount, @PathVariable("starId")String starId) {
        return starService.updateStarLikeCountByStarId(starLikeCount, starId);
    }

    @GetMapping("/starUserId/{starUserId}")
    @ApiOperation(value = "根据主人公编号获取校园明星")
    public Message getStarByStarUserId(@PathVariable("starUserId") String starUserId) {
        return starService.getStarByStarUserId(starUserId);
    }

}
