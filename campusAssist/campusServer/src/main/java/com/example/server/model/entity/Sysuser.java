package com.example.server.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "Sysuser对象", description = "")
public class Sysuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String roleId;

    private String userID;

    private String userName;

    private String passWord;

    private String realName;

    private String protraitPhoto;

    private LocalDateTime birthday;

    private Integer sex;

    private String telephone;

    private String createBy;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String updateBy;

    private String loginIp;

    private Integer delTag;


}
