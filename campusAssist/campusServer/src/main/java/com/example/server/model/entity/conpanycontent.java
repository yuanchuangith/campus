package com.example.server.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
    @Setter
    @ApiModel(value = "conpanycontent", description = "")
    public class conpanycontent implements Serializable {

        private static final long serialVersionUID = 1L;

        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private String conpanyID;
        private  Integer rate;


        private String rateID;

        private String content;

        private String userID;

        private String userName;



        private Integer praiseNum;
    @JsonIgnore
    private Integer rateShow;



    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore

    private LocalDateTime updateTime;
    @JsonIgnore
    private Integer delTag;


    }


