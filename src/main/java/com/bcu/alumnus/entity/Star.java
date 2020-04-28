package com.bcu.alumnus.entity;

import com.fasterxml.jackson.annotation.JsonView;
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

    public interface StarSimpleView extends Message.UnionSimpleView {}


    /**
     * 明星编号
     */
    @ApiModelProperty(value = "明星编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(StarSimpleView.class)
    private Integer starId;

    /**
     * 主人公编号
     */
    @ApiModelProperty(value = "主人公编号")
    @JsonView(StarSimpleView.class)
    private String starUserId;

    /**
     * 学部编号
     */
    @ApiModelProperty(value = "学部编号")
    @JsonView(StarSimpleView.class)
    private String starPartId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @JsonView(StarSimpleView.class)
    private String starTitle;

    /**
     * 封面图片
     */
    @ApiModelProperty(value = "封面图片")
    @JsonView(StarSimpleView.class)
    private String starTitleImg;

    /**
     * 推选单位（例如：信息学部校友会）
     */
    @ApiModelProperty(value = "推选单位（例如：信息学部校友会）")
    @JsonView(StarSimpleView.class)
    private String starHost;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    @JsonView(StarSimpleView.class)
    private Date starCreateDate;

    /**
     * 主人公姓名
     */
    @ApiModelProperty(value = "主人公姓名")
    @JsonView(StarSimpleView.class)
    private String starUserName;

    /**
     * 主人公学部名
     */
    @ApiModelProperty(value = "主人公学部名")
    @JsonView(StarSimpleView.class)
    private String starUserPartName;

    /**
     * 主人公班级名
     */
    @ApiModelProperty(value = "主人公班级名")
    @JsonView(StarSimpleView.class)
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
    @JsonView(StarSimpleView.class)
    private Integer starLikeCount;

    /**
     * 状态（0审核中 1公布）
     */
    @ApiModelProperty(value = "状态（0审核中 1公布）")
    @JsonView(StarSimpleView.class)
    private String starStatus;
}

