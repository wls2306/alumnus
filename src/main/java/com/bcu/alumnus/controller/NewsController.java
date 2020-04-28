package com.bcu.alumnus.controller;

import com.bcu.alumnus.UseToken;
import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.entity.News;
import com.bcu.alumnus.service.NewsService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Level;

@RestController
@Api(tags = "校园新闻模块")
@RequestMapping("/api/news")
public class NewsController {

    @Resource
    private NewsService newsService;

/*
     * 文件上传流程：
     * 客户端请求上传文件，文件上传保存在临时文件夹，返回文件名
     * 文章发布时，携带后台返回的文件名，后台从临时文件夹中将文件移动到业务目录
     * 定时任务，每天凌晨三点清空临时文件夹的所有内容
 */

    @PostMapping("/")
    @ApiOperation(value = "新增校园新闻")
    @UseToken(level = 3)
    public Message<News> insertNews(News news)
    {
        return newsService.insertNews(news);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有校园新闻")
    @JsonView(News.NewsSimpleView.class)
    public Message<List<News>> getAll(){
        return newsService.getAllNews();
    }



    @GetMapping("/")
    @ApiOperation(value = "小程序端获取列表")
    @JsonView(News.NewsSimpleView.class) //只显示类中有@JsonView的字段，避免加载多字段导致传输缓慢
    public Message<List<News>> getNewsForAlumnus(String partId){
        return newsService.getNewsForAlumnus(partId);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据新闻编号获取新闻内容")
    public Message<News> getNewsByNewsId(@PathVariable("id")String newsId){
        return newsService.getNewsDetailByNewsId(newsId);
    }

    @GetMapping("/part/{partId}")
    @ApiOperation(value = "根据学部编号，获取学部内的新闻列表")
    @JsonView(News.NewsSimpleView.class)
    public Message<List<News>> getNewsByPartId(@PathVariable("partId")String partId){
        return newsService.getNewsByPartId(partId);
    }

    @GetMapping("/type/{type}")
    @ApiOperation(value = "根据状态获取校园新闻列表")
    @JsonView(News.NewsSimpleView.class)
    @UseToken(level = 2)
    public Message<List<News>> getNewsByType(@PathVariable("type")String type){
        return newsService.getNewsByNewsType(type);
    }

    @GetMapping("/pub")
    @ApiOperation(value = "根据新闻编号发布新闻")
    @UseToken(level = 4)
    public Message<List<News>> pubNewsByNewsId(String newsId){
        return newsService.publishNewsByNewsId(newsId);
    }

    @GetMapping("/condition")
    @ApiOperation(value = "条件查询新闻")
    @UseToken(level = 2)
    public Message<List<News>> getNewsConditional(String newsId,String newsPartId,String newsType,String newsStatus){
        return newsService.getNewsConditionSearch(newsId, newsPartId, newsType, newsStatus);
    }

    @DeleteMapping("/")
    @ApiOperation(value = "根据编号删除")
    @UseToken(level = 4)
    public Message deleteNewsById(String newsId){
        return newsService.deleteNewsById(newsId);
    }



}
