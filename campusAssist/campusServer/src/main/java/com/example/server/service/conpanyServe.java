package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.example.server.model.entity.conpany;
import com.example.server.model.vo.conpany.conpanyRequstInfo;
import com.example.server.model.vo.conpany.replyConpanyContent;
import com.example.server.util.ResultUtil;

public interface conpanyServe extends IService<conpany> {
    ResultUtil getSearchKeyByConpanyName(String key);
    ResultUtil getConpanyAndRate( conpanyRequstInfo conpanyIndex);
    ResultUtil getSearchByconpanyName( conpanyRequstInfo conpanyIndex);
    ResultUtil getConpanyDetail(conpanyRequstInfo conpanyRequstInfo);
    ResultUtil getconpantContentPage(conpanyRequstInfo conpanyRequstInfo);
    ResultUtil addPrais(String rateID,int index);

    ResultUtil replyContent(replyConpanyContent reply);
}
