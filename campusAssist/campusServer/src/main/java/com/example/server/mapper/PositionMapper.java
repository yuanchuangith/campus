package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Position;
import com.example.server.model.entity.Positioncontents;
import com.example.server.model.view.positionContent.getContentCount;
import com.example.server.model.view.positionContent.getPositionContents;
import com.example.server.model.view.positionContent.getReplyPositionContents;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    ArrayList<getPositionContents> getPositionContent(String jobID,int starpage,int endpage);
    ArrayList<getReplyPositionContents> getGroupBycontentIdReply(String jobID ,int starpage,int endpage);
    ArrayList<getContentCount> getContentCount(String jobID,int starpage,int endpage);
    ArrayList<getReplyPositionContents> getcontentIdReply(String contentID ,int starpage,int endpage);

}
