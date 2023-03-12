package com.example.server.util;

import com.example.server.model.LoginUser;
import com.example.server.model.view.accountEntity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Component
public class getLoginUser {
    @Autowired
    private com.example.server.mapper.userMapper userMapper;
    public LoginUser getLoginUserByName(String userName,int tag)
    {
      if (tag==0)
      {
          Userinfo user =userMapper.getUser(userName);
          if (Objects.isNull(user)){
              return null;
          }
          else {
              List<String> auth = new ArrayList<>(Arrays.asList("admin","test"));
              return new LoginUser(user,auth);
          }
      }
      else if (tag==1)
      {
          return null;
      }
      else
      {
          return null;
      }
    }
    public LoginUser getLoginUserById(String userID,int tag)
    {
        if (tag==0)
        {
            Userinfo user =userMapper.getUserByID(userID);
            if (Objects.isNull(user)){
                return null;
            }
            else {
                List<String> auth = new ArrayList<>(Arrays.asList("admin","test"));
                return new LoginUser(user,auth);
            }
        }
        else if (tag==1)
        {
            return null;
        }
        else
        {
            return null;
        }
    }
    public LoginUser getLoginUserByPhone(String phone , int tag)
    {
        return  null;
    }
}
