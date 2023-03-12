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
@ApiModel(value = "Obedientclassification对象", description = "")
public class Obedientclassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String classificationID;

    private String description;

    private String title;

    private String textContent;

    private String images;

    private Integer peopleNum;

    private Integer heat;


    private LocalDateTime createTime;
    @JsonIgnore

    private LocalDateTime updateTime;
    @JsonIgnore
    private Integer delTag;


}
