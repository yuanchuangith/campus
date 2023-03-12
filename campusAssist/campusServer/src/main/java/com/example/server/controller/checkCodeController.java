package com.example.server.controller;

import com.example.server.model.entity.User;
import com.example.server.service.checkCodeServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("serverCode")
public class checkCodeController {

    @Autowired
    private checkCodeServe checkCodeSever;

    @PostMapping("getCheckToken")
    public ResultUtil getCheckToken (@RequestBody User user, HttpServletRequest request)
    {

        return checkCodeSever.getToken(request,user);
    }
    @PostMapping("chatGpt")
    public ResultUtil chatGpt (@RequestBody String key, HttpServletRequest request)
    {

        return checkCodeSever.getChat(key);
    }
}
