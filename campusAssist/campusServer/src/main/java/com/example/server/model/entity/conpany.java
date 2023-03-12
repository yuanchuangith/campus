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
    @ApiModel(value = "conpany", description = "")
    public class conpany implements Serializable {

        private static final long serialVersionUID = 1L;

        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private String conpanyName;
        private  Integer jobNum;


        private String conpanyID;

        private String conpanySize;

        private String provice;

        private String conpanyCharacter;

        private String conpanyIndustry;
        private String conpanyInfo;

        private Integer praiseNum;

        private Double rate;
    @JsonIgnore
        private Integer conpanyShow;

    private LocalDateTime createTime;
    @JsonIgnore

    private LocalDateTime updateTime;
    @JsonIgnore
    private Integer delTag;


    }


