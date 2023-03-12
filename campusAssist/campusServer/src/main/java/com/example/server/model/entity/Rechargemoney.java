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
 * @since 2023-02-24
 */
@Getter
@Setter
@ApiModel(value = "Rechargemoney对象", description = "")
public class Rechargemoney implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String moneyID;

    private String userID;

    private BigDecimal chargeMoney;

    private Integer trialCode;

    private Integer trialChat;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delTag;


}
