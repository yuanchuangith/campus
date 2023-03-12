package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Positioncontents;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionContentsMapper extends BaseMapper<Positioncontents> {
   int addPriase(String contentID);
}
