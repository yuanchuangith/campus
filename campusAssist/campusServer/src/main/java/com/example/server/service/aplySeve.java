package com.example.server.service;


import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.model.entity.Aply;
import com.example.server.model.vo.rechage.reapply;
import com.example.server.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface aplySeve extends IService<Aply> {
    ResultUtil chargeMoney(HttpServletResponse response , reapply reapply) throws IOException, AlipayApiException;
    ResultUtil notify(HttpServletRequest request) throws Exception;
}
