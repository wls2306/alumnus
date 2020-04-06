package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-Apply")
@Entity
@Data
public class Apply {
    /**
     * 报名编号
     */
    @ApiModelProperty(value = "报名编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyId;

    /**
     * 活动编号
     */
    @ApiModelProperty(value = "活动编号")
    private String applyActivityId;

    /**
     * 报名者学号
     */
    @ApiModelProperty(value = "报名者学号")
    private String applyUserId;

    /**
     * 报名者姓名
     */
    @ApiModelProperty(value = "报名者姓名")
    private String applyUserName;

    /**
     * 报名者学部名称
     */
    @ApiModelProperty(value = "报名者学部名称")
    private String applyUserPart;

    /**
     * 报名者班级名称
     */
    @ApiModelProperty(value = "报名者班级名称")
    private String applyUserClass;

    /**
     * 报名者手机号
     */
    @ApiModelProperty(value = "报名者手机号")
    private String applyUserPhone;

    /**
     * 报名者邮箱
     */
    @ApiModelProperty(value = "报名者邮箱")
    private String applyUserEmail;

    /**
     * 报名提交时间
     */
    @ApiModelProperty(value = "报名提交时间")
    private Date applyCreateTime;

    /**
     * 报名状态（-1拒绝 0待审核 1已录取）
     */
    @ApiModelProperty(value = "报名状态（-1拒绝 0待审核 1已录取）")
    private String applyStatus;

    /**
     * 备注（拒绝理由）
     */
    @ApiModelProperty(value = "备注（拒绝理由）")
    private String applyRemark;
}

