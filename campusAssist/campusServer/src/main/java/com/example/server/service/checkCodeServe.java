package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.model.entity.User;
import com.example.server.model.entity.conpany;
import com.example.server.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

public interface checkCodeServe extends IService<conpany> {
    ResultUtil getToken(HttpServletRequest request, User user);
    ResultUtil getChat(String key);
}
