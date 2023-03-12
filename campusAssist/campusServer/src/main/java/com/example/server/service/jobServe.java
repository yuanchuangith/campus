package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.example.server.model.entity.Position;
import com.example.server.model.vo.Contents.getReplyContent;
import com.example.server.model.vo.Contents.replayContent;
import com.example.server.model.vo.workEntity.getJobInfoAndContent;
import com.example.server.model.vo.workEntity.getPositionEntity;
import com.example.server.model.vo.workEntity.searchContent;
import com.example.server.util.ResultUtil;


public interface jobServe extends IService<Position> {
    ResultUtil getProvices();
    ResultUtil getJobRequierInfo();
    ResultUtil getPosition();
    ResultUtil getWorkContent();
    ResultUtil getNewWorkContent();
    ResultUtil getPosiyionNumRank();
    ResultUtil getWorkProvices();
    ResultUtil getIndexPosition(getPositionEntity entity);
    ResultUtil getIndustrykind();
    ResultUtil getSearchContent(searchContent searchContent);

    ResultUtil getIndexSearchPosition (getPositionEntity entity);
    ResultUtil getPositionDetail (getJobInfoAndContent getJobInfoAndContent);
    ResultUtil getReplyContent (getReplyContent getReplyContent);
    ResultUtil saveContentReply (replayContent replayContent);
    ResultUtil addPrise(String contentID,String replayID,int model,String jobID);
}
