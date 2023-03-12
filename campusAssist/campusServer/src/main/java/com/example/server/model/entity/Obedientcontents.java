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
@ApiModel(value = "Obedientcontents对象", description = "")
public class Obedientcontents implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String contentID;

    private String obedientID;

    private String userID;

    private String content;

    private Integer comtentshow;

    private String imgages;

    private String replyToPerson;

    private Integer praiseNum;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore

    private LocalDateTime updateTime;
    @JsonIgnore
    private Integer delTag;


}
