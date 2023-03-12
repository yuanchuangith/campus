package com.example.server.model;

import com.example.server.mapper.userMapper;
import com.example.server.model.view.accountEntity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServerDetiala implements UserDetailsService {

  @Autowired
   private userMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] usernames = username.split("\\|");
        Userinfo user =null;
        username=usernames[0];
        if (usernames[1].equals("0"))
        {
//            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.eq(User::getUserName, usernames[0]).last("limit 1");
            user =  userMapper.getUser(username);

        }
        else if(usernames[1]=="1")
        {

        }



        if(user.getUserName()==null){
            throw new RuntimeException("用户名错误");
        }
       //授权认证

        List <String> auth = new ArrayList<>(Arrays.asList("admin","test"));
        return new LoginUser(user,auth);
    }
}
