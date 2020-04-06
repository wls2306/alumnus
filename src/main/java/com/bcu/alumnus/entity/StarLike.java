package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-StarLike")
@Entity
@Data
public class StarLike {
    /**
     * 记录编号
     */
    @ApiModelProperty(value = "记录编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 明星编号
     */
    @ApiModelProperty(value = "明星编号")
    private Integer starId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userId;
}

