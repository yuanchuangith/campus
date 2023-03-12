package com.example.server.model.view.accountEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2022-12-05
 */
@Getter
@Setter
@ApiModel(value = "User", description = "")

public class Userinfo implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userID;

    private String userName;

    @JsonIgnore

    private String passWord;

    private String realName;

    private String protraitPhoto;

    private LocalDateTime birthday;

    private Integer sex;

    private String telephone;
    @JsonIgnore
    private Integer locked;
    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    @JsonIgnore
    private String updateBy;

    private String loginIp;
    @JsonIgnore
    private Integer onceUpdateSex;
    @JsonIgnore
    private Integer delTag;


}
