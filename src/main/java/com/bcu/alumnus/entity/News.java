package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-News")
@Entity
@Data
public class News {
    /**
     * 新闻编号
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "新闻编号")
    @Id
    private Integer newsId;

    /**
     * 学部编号
     */
    @ApiModelProperty(value = "学部编号")
    private String newsPartId;

    /**
     * 新闻标题
     */
    @ApiModelProperty(value = "新闻标题")
    private String newsTitle;

    /**
     * 新闻作者（姓名/学部号）
     */
    @ApiModelProperty(value = "新闻作者（姓名/学部号）")
    private String newsAuthor;

    /**
     * 新闻封面
     */
    @ApiModelProperty(value = "新闻封面")
    private String newsTitleImg;

    /**
     * 新闻轮播图
     */
    @ApiModelProperty(value = "新闻轮播图")
    private String newsContentImg;

    /**
     * 新闻内容
     */
    @ApiModelProperty(value = "新闻内容")
    private String newsContent;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    private Date newsData;

    /**
     * 新闻类别（1为学部 2为2全部）
     */
    @ApiModelProperty(value = "新闻类别（1为学部 2为2全部）")
    private String newsType;

    /**
     * 新闻状态（0为不可见 1为可见）
     */
    @ApiModelProperty(value = "新闻状态（0为不可见 1为可见）")
    private String newsStatus;
}

