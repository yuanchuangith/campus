package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Rechargemoney;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface reChargeMoneyMapper  extends BaseMapper<Rechargemoney> {
   void updateRechargemoney(String userID, float   money);
}
