package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-Clazz")
@Entity
@Data
public class Clazz {
    /**
     * 班级编号
     */
    @Id
    @ApiModelProperty(value = "班级编号")
    private Integer clazzId;

    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级名称")
    private String clazzName;
}

