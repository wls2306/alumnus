package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.StarLike;
import com.bcu.alumnus.service.StarLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "校园明星点赞模块")
@RequestMapping("api/starLike")
public class StarLikeController {

    @Resource
    private StarLikeService starLikeService;

    @PostMapping("/add")
    @ApiOperation(value = "添加点赞记录")
    @UseToken(level = 1)
    public Message insertStarLike(StarLike starLike) {
        return starLikeService.insertStarLike(starLike);
    }

    @GetMapping("/starId/{starId}")
    @ApiOperation(value = "通过明星编号查找点赞记录")
    public Message getStarLikeByStarId(@PathVariable("starId")int starId) {
        return starLikeService.getStarLikeByStarId(starId);
    }

    @GetMapping("/countByStarId/{cStarId}")
    @ApiOperation(value = "通过明星编号统计点赞数量")
    public Message getStarLikeCountByStarId(@PathVariable("cStarId")String starId) {
        return starLikeService.getStarLikeCountByStarId(starId);
    }

    @GetMapping("/userId/{userId}")
    @ApiOperation(value = "通过用户编号查找点赞记录")
    public Message getStarLikeByUserId(@PathVariable("userId")String userId) {
        return starLikeService.getStarLikeByUserId(userId);
    }

    @GetMapping("/countByUserId/{cUserId}")
    @ApiOperation(value = "通过用户编号统计点赞数量")
    public Message getStarLikeCountByUserId(@PathVariable("cUserId")String userId) {
        return starLikeService.getStarLikeCountByUserId(userId);
    }

    @PostMapping("/delete/{id}/{dUserId}")
    @ApiOperation(value = "通过点赞编号、用户编号删除该点赞记录")
    @UseToken(level = 1)
    public Message deleteStarLikeByIdAndUserId(@PathVariable("id")int id, @PathVariable("dUserId")String userId) {
        return starLikeService.deleteByIdAndUserId(id, userId);
    }
}
