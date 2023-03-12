package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.RechargeTrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReChargeTradeMapper extends BaseMapper<RechargeTrade> {
    RechargeTrade getoreder(String out_trade_no);
    void  updateRechargeTrade(int tradeModel,String setTradeNo,String setBuyerId,String setTimeStampT,String outTradeNo);
}
