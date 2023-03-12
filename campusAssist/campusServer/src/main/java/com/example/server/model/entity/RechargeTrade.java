package com.example.server.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2023-02-23
 */
@Getter
@Setter
@TableName("rechargeTrade")
@ApiModel(value = "RechargeTrade对象", description = "")
public class RechargeTrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String tradeNo;

    private String outTradeNo;
    private  String userID;

    private BigDecimal totalAmount;
    @JsonIgnore
    private String appId;

    private Integer tradeModel;

    private String tradeName;

    private LocalDateTime createTime;
    @JsonIgnore
    private String buyerId;

    private LocalDateTime timeStampT;
    @JsonIgnore
    private Integer delTag;


}
