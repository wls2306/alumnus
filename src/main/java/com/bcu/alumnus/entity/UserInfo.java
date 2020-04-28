package com.bcu.alumnus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "com-bcu-alumnus-entity-UserInfo")
@Entity
@Data
@Valid
public class UserInfo {
    /**
     * 用户编号（学号）
     */
    @ApiModelProperty(value = "用户编号（学号）")
    @Id
    @NotBlank(message = "用户编号不得为空！")
    private String userId;

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户姓名不得为空！")
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private String userSex;

    /**
     * 生源地（省市区）
     */
    @ApiModelProperty(value = "生源地（省市区）")
    private String userOrigin;

    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级名称")
    private String userClassName;

    /**
     * 学部名称
     */
    @ApiModelProperty(value = "学部名称")
    private String userPartName;

    /**
     * 用户级（入学年份）
     */
    @ApiModelProperty(value = "用户级（入学年份）")
    private String userLevel;

    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date userBirthday;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    @NotBlank(message = "用户手机号不得为空！")
    private String userPhone;

    /**
     * 用户邮箱
     */
    @NotBlank(message = "用户邮箱不得为空！")
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;

    /**
     * 上一次资料更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "上一次资料更新时间")
    private Date userUpdateTime;
}

