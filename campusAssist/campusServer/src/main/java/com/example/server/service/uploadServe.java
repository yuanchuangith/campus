package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.model.entity.User;
import com.example.server.util.ResultUtil;
import org.springframework.web.multipart.MultipartFile;

public interface uploadServe extends IService<User> {
    ResultUtil uploadavatar(MultipartFile file, String userID);
}
