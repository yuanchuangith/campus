package com.example.server.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2022-12-21
 */
@Getter
@Setter
@ApiModel(value = "Position对象", description = "")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @JsonIgnore
    private Integer id;

    private String positionID;

    private String jobID;

    private String description;

    private String textContent;

    private String images;

    private Integer interestedNum;

    private Integer praiseNum;

    private String source;
    @JsonIgnore
    private Integer positionshow;

    private String wage;

    private String url;

    private String jobaddress;

    private String tag;

    private String companyName;

    private String companyInfo;

    private String companyCharacter;

    private String companySize;

    private String companyIndustry;

    private String companyLocation;

    private String companyLocarionArea;

    private String provice;

    private String compeducationRequire;

    private String workRequireExperience;

    private String wekare;

    private String addressUrl;

    private LocalDateTime publishData;

    private String kind;

    private Integer scale;

    private String classification;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore

    private LocalDateTime updateTime;
    @JsonIgnore
    private Integer delTag;


}
