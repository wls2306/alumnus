package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-Star")
@Data
@Entity
public class Star {
    /**
     * 明星编号
     */
    @ApiModelProperty(value = "明星编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer starId;

    /**
     * 主人公编号
     */
    @ApiModelProperty(value = "主人公编号")
    private String starUserId;

    /**
     * 学部编号
     */
    @ApiModelProperty(value = "学部编号")
    private String starPartId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String starTitle;

    /**
     * 封面图片
     */
    @ApiModelProperty(value = "封面图片")
    private String starTitleImg;

    /**
     * 推选单位（例如：信息学部校友会）
     */
    @ApiModelProperty(value = "推选单位（例如：信息学部校友会）")
    private String starHost;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    private Date starCreateDate;

    /**
     * 主人公姓名
     */
    @ApiModelProperty(value = "主人公姓名")
    private String starUserName;

    /**
     * 主人公学部名
     */
    @ApiModelProperty(value = "主人公学部名")
    private String starUserPartName;

    /**
     * 主人公班级名
     */
    @ApiModelProperty(value = "主人公班级名")
    private String starUserClassName;

    /**
     * 主人公经历
     */
    @ApiModelProperty(value = "主人公经历")
    private String starExperience;

    /**
     * 生活照片轮播
     */
    @ApiModelProperty(value = "生活照片轮播")
    private String starImg;

    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量")
    private Integer starLikeCount;

    /**
     * 状态（0审核中 1公布）
     */
    @ApiModelProperty(value = "状态（0审核中 1公布）")
    private String starStatus;
}

