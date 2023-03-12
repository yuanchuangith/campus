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
@ApiModel(value = "Obedienttemp对象", description = "")
public class Obedienttemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String obedientID;

    private String userID;

    private String title;

    private String textContent;

    private String images;

    private Integer praiseNum;

    private Integer contenrtNum;

    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore

    private LocalDateTime updateTime;
    @JsonIgnore
    private Integer delTag;


}
