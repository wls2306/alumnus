package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-Activity")
@Entity
@Data
public class Activity {

    public interface ActivitySimpleView extends Message.UnionSimpleView{};


    /**
     * 活动编号
     */
    @ApiModelProperty(value = "活动编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actId;

    /**
     * 活动学部编号
     */
    @ApiModelProperty(value = "活动学部编号")
    private String actPartId;

    /**
     * 活动创建人编号
     */
    @ApiModelProperty(value = "活动创建人编号")
    private String actUserId;

    /**
     * 活动封面
     */
    @ApiModelProperty(value = "活动封面")
    private String actTitleImg;

    /**
     * 活动标题
     */
    @ApiModelProperty(value = "活动标题")
    private String actTitle;

    /**
     * 活动轮播图
     */
    @ApiModelProperty(value = "活动轮播图")
    private String actContentImg;

    /**
     * 活动主办方（管理员创建即为学部名称，校友创建即为校友姓名）
     */
    @ApiModelProperty(value = "活动主办方（管理员创建即为学部名称，校友创建即为校友姓名）")
    private String actHost;

    /**
     * 活动报名截止日期
     */
    @ApiModelProperty(value = "活动报名截止日期")
    private Date actJoinDeadtime;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    private Date actStartTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间")
    private Date actStopTime;

    /**
     * 活动最大人数
     */
    @ApiModelProperty(value = "活动最大人数")
    private Integer actMemberMax;

    /**
     * 活动录取形式（1需要审核，2不需要审核）
     */
    @ApiModelProperty(value = "活动录取形式（1需要审核，2不需要审核）")
    private String actMemberWay;

    /**
     * 活动简介
     */
    @ApiModelProperty(value = "活动简介")
    private String actDesc;

    /**
     * 活动负责人姓名
     */
    @ApiModelProperty(value = "活动负责人姓名")
    private String actPrincipalName;

    /**
     * 活动负责人电话
     */
    @ApiModelProperty(value = "活动负责人电话")
    private String actPrincipalPhone;

    /**
     * 活动负责人邮箱
     */
    @ApiModelProperty(value = "活动负责人邮箱")
    private String actPrincipalEmail;

    /**
     * 活动地点
     */
    @ApiModelProperty(value = "活动地点")
    private String actAddress;

    /**
     * 地点经纬度（管理端获取）
     */
    @ApiModelProperty(value = "地点经纬度（管理端获取）")
    private String actCoordinate;

    /**
     * 活动创建时间
     */
    @ApiModelProperty(value = "活动创建时间")
    private Date actCreateTime;

    /**
     * 活动类型（1.院系级 2.校级）
     */
    @ApiModelProperty(value = "活动类型（1.院系级 2.校级）")
    private String actType;

    /**
     * 活动状态（0待审核 1报名中 2已截止 3已结束）
     */
    @ApiModelProperty(value = "活动状态（0待审核 1报名中 2已截止 3已结束）")
    private String actStatus;
}

