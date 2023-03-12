package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.example.server.model.view.accountEntity.userLogin;
import com.example.server.model.view.accountEntity.userRegister;
import com.example.server.model.entity.User;
import com.example.server.model.vo.user.updatePW;
import com.example.server.model.vo.user.updateUserInfo;
import com.example.server.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

public interface userServe extends IService<User> {
    ResultUtil login(userLogin userLogin,HttpServletRequest request);
    ResultUtil insertUser(userRegister userRegister, HttpServletRequest request);
    ResultUtil getUserInfo(HttpServletRequest request) throws Exception;
    ResultUtil updatePassWord(updatePW updatePW) throws Exception;
    ResultUtil updateUserInfo(updateUserInfo userInfo) throws Exception;
    ResultUtil userDetailInfo(String userID);
    ResultUtil getOrder(String userID,String model);
    ResultUtil getContent(String userID);
}
