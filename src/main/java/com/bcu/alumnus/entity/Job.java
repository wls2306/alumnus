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

@ApiModel(value = "com-bcu-alumnus-entity-Job")
@Entity
@Data
public class Job {

    public interface JobSimpleView extends Message.UnionSimpleView {};

    /**
     * 岗位编号
     */
    @ApiModelProperty(value = "岗位编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JobSimpleView.class)
    private Integer jobId;

    /**
     * 学部编号
     */
    @ApiModelProperty(value = "学部编号")
    private String jobPartId;

    /**
     * 推荐人编号
     */
    @ApiModelProperty(value = "推荐人编号")
    private String jobUserId;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String jobName;

    /**
     * 岗位公司
     */
    @ApiModelProperty(value = "岗位公司")
    private String jobCompany;

    /**
     * 岗位薪资
     */
    @ApiModelProperty(value = "岗位薪资")
    private String jobSalary;

    /**
     * 工作地点
     */
    @ApiModelProperty(value = "工作地点")
    private String jobAddress;

    /**
     * 工作经验
     */
    @ApiModelProperty(value = "工作经验")
    private String jobExperience;

    /**
     * 学历要求
     */
    @ApiModelProperty(value = "学历要求")
    private String jobEducation;

    /**
     * 职位详情
     */
    @ApiModelProperty(value = "职位详情")
    private String jobDetail;

    /**
     * 工作环境轮播
     */
    @ApiModelProperty(value = "工作环境轮播")
    private String jobEnviromentImg;

    /**
     * 推荐人名称
     */
    @ApiModelProperty(value = "推荐人名称,")
    private String jobUserName;

    /**
     * 推荐人手机
     */
    @ApiModelProperty(value = "推荐人手机")
    private String jobUserPhone;

    /**
     * 推荐人邮箱
     */
    @ApiModelProperty(value = "推荐人邮箱")
    private String jobUserEmail;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    private Date jobCreateDate;

    /**
     * 岗位类型（1学部可见 2全部）
     */
    @ApiModelProperty(value = "岗位类型（1学部可见 2全部）")
    private String jobType;

    /**
     * 岗位状态（0审核（不可见） 1发布（可见））
     */
    @ApiModelProperty(value = "岗位状态（0审核（不可见） 1发布（可见））")
    private String jobStatus;
}

