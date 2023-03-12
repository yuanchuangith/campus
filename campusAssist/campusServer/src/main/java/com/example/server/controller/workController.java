package com.example.server.controller;

import com.example.server.model.vo.Contents.getReplyContent;
import com.example.server.model.vo.workEntity.getJobInfoAndContent;
import com.example.server.model.vo.workEntity.getPositionEntity;
import com.example.server.model.vo.workEntity.searchContent;
import com.example.server.service.jobServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work")
public class workController {

    @Autowired
    private jobServe jobServer;



    @GetMapping("/getProviceInfo")
    public ResultUtil getProviceInfo()
    {
        ResultUtil resultUtil=jobServer.getProvices();

        return resultUtil;
    }

    @GetMapping("/JobRequier")
    public ResultUtil JobRequier()
    {
        ResultUtil resultUtil=jobServer.getJobRequierInfo();

        return resultUtil;
    }
    @GetMapping("/scrollPosition")
    public ResultUtil scrollPosition()
    {
        ResultUtil resultUtil=jobServer.getPosition();

        return resultUtil;
    }
    @GetMapping("/positContent")
    public ResultUtil positContent()
    {
        ResultUtil resultUtil=jobServer.getWorkContent();

        return resultUtil;
    }

    @GetMapping("/newWorkContent")
    public ResultUtil newWorkContent()
    {
        ResultUtil resultUtil=jobServer.getNewWorkContent();

        return resultUtil;
    }
//    getPosiyionNumRank
@GetMapping("/getPosiyionRank")
public ResultUtil getPosiyionRank()
{
    ResultUtil resultUtil=jobServer.getPosiyionNumRank();

    return resultUtil;
}
    @GetMapping("/getWorkProvices")
    public ResultUtil getWorkProvice()
    {
        ResultUtil resultUtil=jobServer.getWorkProvices();

        return resultUtil;
    }
    @PostMapping("/getIndexPosition")
    public ResultUtil getIndexPosition(@RequestBody getPositionEntity getPositionEntity)
    {
        ResultUtil resultUtil=jobServer.getIndexPosition(getPositionEntity);

        return resultUtil;
    }
    @GetMapping("/getIndustrykind")
    public ResultUtil getIndustrykind()
    {
        ResultUtil resultUtil=jobServer.getIndustrykind();

        return resultUtil;
    }
    @PostMapping("/getSearchTips")
    public ResultUtil getSearchTips(@RequestBody searchContent searchContent)
    {
        ResultUtil resultUtil=jobServer.getSearchContent(searchContent);

        return resultUtil;
    }

    @PostMapping("/getIndexSearchPosition")
    public ResultUtil getIndexSearchPosition(@RequestBody getPositionEntity getPositionEntity)
    {
        ResultUtil resultUtil=jobServer.getIndexSearchPosition(getPositionEntity);

        return resultUtil;
    }
    @PostMapping("/getJobDetail")
    public ResultUtil getJobDetail(@RequestBody getJobInfoAndContent getPositionEntity)
    {
        ResultUtil resultUtil=jobServer.getPositionDetail(getPositionEntity);

        return resultUtil;
    }
    @PostMapping("/getReplyContent")
    public ResultUtil getReplyContent(@RequestBody getReplyContent getReplyContent)
    {
        ResultUtil resultUtil=jobServer.getReplyContent(getReplyContent);

        return resultUtil;
    }
}
