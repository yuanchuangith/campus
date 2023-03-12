package com.example.server.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2023-02-10
 */
@Getter
@Setter
@ApiModel(value = "Aply对象", description = "")
public class Aply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderID;
    private String aplyID;
    private String userID;
    private int orderstate;
    private String  merchant;
    private BigDecimal rechargeMoney;

    private Integer ordershow;

    private String way;

    private String remarks;

    private LocalDateTime createTime;
    private LocalDateTime palyTime;

    private LocalDateTime updateTime;

    private Integer delTag;


}
