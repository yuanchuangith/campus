package com.example.server.controller;

import com.example.server.model.vo.conpany.conpanyRequstInfo;
import com.example.server.model.vo.conpany.replyConpanyContent;
import com.example.server.service.conpanyServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conpany")
public class conpanyController {
    @Autowired
    private conpanyServe conpanyServer;
    @PostMapping("getSearchKeyByConpanyName")
    public ResultUtil getSearchKeyByConpanyName( @RequestBody  String key)
    {
        return  conpanyServer.getSearchKeyByConpanyName(key);
    }
    @PostMapping("getConapnyAndrate")
    public ResultUtil getConapnyAndrate( @RequestBody conpanyRequstInfo conpanyIndex )
    {
        return  conpanyServer.getConpanyAndRate(conpanyIndex);
    }
    @PostMapping("getSearchByconpanyName")
    public ResultUtil getSearchByconpanyName( @RequestBody conpanyRequstInfo conpanyIndex )
    {
        return  conpanyServer.getSearchByconpanyName(conpanyIndex);
    }
    @PostMapping("getConpanyDetail")
    public ResultUtil getConpanyDetail( @RequestBody conpanyRequstInfo conpanyIndex )
    {
        return  conpanyServer.getConpanyDetail(conpanyIndex);
    }
    @PostMapping("getconpantContentPage")
    public ResultUtil getconpantContentPage(@RequestBody  conpanyRequstInfo conpanyRequstInfo)
    {
        return  conpanyServer.getconpantContentPage(conpanyRequstInfo);
    }

    @GetMapping("praise")
    public ResultUtil addPraise(String rateID,int index)
    {
        return  conpanyServer.addPrais(rateID,index);
    }
    @PostMapping("/replyConten")
    public  ResultUtil replyContent(@RequestBody replyConpanyContent reply)
    {
        return  conpanyServer.replyContent(reply);
    }

}
