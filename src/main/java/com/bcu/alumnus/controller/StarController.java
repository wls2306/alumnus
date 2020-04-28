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
import java.util.List;

@RestController
@Api(tags = "校园明星模块")
@RequestMapping("/api/star")
public class StarController {

    @Resource
    private StarService starService;

    @PostMapping("/add")
    @ApiOperation(value = "新增校园明星")
    @UseToken(level = 2)
    public Message<Star> insertStar(Star star) {
        return starService.insertStar(star);
    }

    @GetMapping("/pass/part/{partId}")
    @ApiOperation(value = "通过学部编号查找状态为可见的校园明星")
    @JsonView(Star.StarSimpleView.class)
    public Message<List<Star>> getStarForAlumnus(@PathVariable("partId")String partId) {
        return starService.getStarByStarPartIdAndAndStarStatus(partId);
    }

    @PutMapping("/verify")
    @ApiOperation(value = "发布明星根据编号(审核)")
    @UseToken(level = 4)
    public Message<?> publishStarByStarId(int starId) {
        return starService.publishStarByStarId(starId);
    }

    @DeleteMapping("/{starId}")
    @ApiOperation(value = "根据编号删除校园明星")
    @UseToken(level = 4)
    public Message<?> deleteStarByStarId(@PathVariable("starId")int starId) {
        return starService.deleteStarByStarId(starId);
    }

    @GetMapping("/pass/all")
    @ApiOperation(value = "查找所有状态为可见的校园明星")
    @JsonView(Star.StarSimpleView.class)
    public Message<List<Star>> getAllStar() {
        return starService.getAllStar("1");
    }

    @GetMapping("/id/{starId}")
    @ApiOperation(value = "根据明星编号获取校园明星")
    public Message<Star> getStarByStarId(@PathVariable("starId")int starId) {
        return starService.getStarByStarId(starId);
    }

   /* @GetMapping("/userId/{userId}")
    @ApiOperation(value = "根据用户编号获取校园明星")
    public Message getStarByUserId(@PathVariable("userId") String userId) {
        return starService.getStarByUserId(userId);
    }*/

    @GetMapping("/part/{starPartId}")
    @ApiOperation(value = "根据学部编号获取校园明星")
    @JsonView(Star.StarSimpleView.class)
    @UseToken(level = 3)
    public Message<List<Star>> getStarByStarPartId(@PathVariable("starPartId") String starPartId) {
        return starService.getStarByStarPartId(starPartId);
    }

    @GetMapping("/status/{starStatus}")
    @ApiOperation(value = "根据可见状态获取校园明星")
    @JsonView(Star.StarSimpleView.class)
    @UseToken(level = 2)
    public Message<List<Star>> getStarByStarStatus(@PathVariable("starStatus")String starStatus) {
        return starService.getStarByStarStatus(starStatus);
    }

    @PutMapping("/like/{starId}")
    @ApiOperation(value = "根据校园明星编号更新点赞数量")
    @UseToken(level = 1)
    public Message<?> updateStarLikeCountByStarId(@PathVariable("starId")int starId) {
        return starService.updateStarLikeCountByStarId(starId);
    }

    @GetMapping("/user/{starUserId}")
    @ApiOperation(value = "根据主人公编号获取校园明星")
    @JsonView(Star.StarSimpleView.class)
    @UseToken(level = 1)
    public Message<List<Star>> getStarByStarUserId(@PathVariable("starUserId") String starUserId) {
        return starService.getStarByStarUserId(starUserId);
    }

}
