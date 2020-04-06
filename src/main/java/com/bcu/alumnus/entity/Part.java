package com.bcu.alumnus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-bcu-alumnus-entity-Part")
@Entity
@Data
public class Part {
    /**
     * 学部编号
     */
    @ApiModelProperty(value = "学部编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;

    /**
     * 学部名称
     */
    @ApiModelProperty(value = "学部名称")
    private String partName;

    /**
     * 学部负责人姓名
     */
    @ApiModelProperty(value = "学部负责人姓名")
    private String partPrincipalName;

    /**
     * 学部负责人电话
     */
    @ApiModelProperty(value = "学部负责人电话")
    private String partPrincipaiPhone;
}

