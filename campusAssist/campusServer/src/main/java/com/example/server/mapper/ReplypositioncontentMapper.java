package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Replypositioncontent;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplypositioncontentMapper extends BaseMapper<Replypositioncontent> {
    int addPriase(String replyID);
}
