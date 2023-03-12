package com.example.server.controller;

import com.example.server.model.vo.Contents.replayContent;
import com.example.server.service.jobServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class contentController {
    @Autowired
    private jobServe jobServer;


    @PostMapping("/saveContent")
    public ResultUtil saveContent(replayContent replayContent)
    {
        ResultUtil resultUtil=  jobServer.saveContentReply(replayContent);
        return  resultUtil;
    }
    @GetMapping("/praise")
    public ResultUtil praise(String contentID,String replayID ,int model,String jobID){
        ResultUtil resultUtil = jobServer.addPrise(contentID,replayID,model,jobID);
        return resultUtil;
    }
}
