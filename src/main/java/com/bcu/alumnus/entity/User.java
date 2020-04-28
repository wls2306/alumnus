package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-User")
@Data
@Entity
public class User {

    public interface UserSimpleView extends Message.UnionSimpleView{}

    /**
     * 用户编号（校友为学号，教师为工号）
     */
    @ApiModelProperty(value = "用户编号（校友为学号，教师为工号）")
    @Id
    private String userId;

    /**
     * 小程序openid
     */
    @ApiModelProperty(value = "小程序openid")
    private String userOpenId;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 班级编号
     */
    @ApiModelProperty(value = "班级编号")
    private String userClassId;

    /**
     * 学部编号
     */
    @ApiModelProperty(value = "学部编号")
    private String userPartId;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    /**
     * 用户类别
     */
    @ApiModelProperty(value = "用户类别")
    private String userType;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private String userStatus;

    /**
     * 用户备注
     */
    @ApiModelProperty(value = "用户备注")
    private String userRemark;
}

