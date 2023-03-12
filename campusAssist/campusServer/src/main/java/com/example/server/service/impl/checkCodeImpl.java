package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.conpanyMapper;
import com.example.server.model.entity.User;
import com.example.server.model.entity.conpany;
import com.example.server.service.checkCodeServe;
import com.example.server.util.JwtUtil;
import com.example.server.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class checkCodeImpl extends ServiceImpl<conpanyMapper, conpany> implements checkCodeServe {
    @Override
    public ResultUtil getToken(HttpServletRequest request , User user) {

        if(Objects.isNull(user.getUserID())|| Objects.isNull(user.getUserName()))
            return ResultUtil.fail(400061);
        String  jwt= JwtUtil.CreateJwtNoTime(user.getUserID()+"|"+user.getUserName());



        return ResultUtil.success(200,"成功",jwt);
    }

    @Override
    public ResultUtil getChat(String key) {
//        CloseableHttpClient client =new HttpClientBuilder.create().bulid() ：
        return null;
    }
}
