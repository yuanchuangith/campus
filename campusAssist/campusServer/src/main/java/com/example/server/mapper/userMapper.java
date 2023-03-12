package com.example.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.example.server.model.entity.RechargeTrade;
import com.example.server.model.view.accountEntity.Userinfo;
import com.example.server.model.entity.User;
import com.example.server.model.view.userEntity.getContentInfo;
import com.example.server.model.vo.user.userContentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface userMapper extends BaseMapper<User> {
    List<User> querUserList();
    Userinfo getUser(String  name );
    Userinfo getUserByID(String  userid );
     void updatePW (String passWord,String userid);
    void updateAvatar (String imgName,String userid);
    void updateUserInfo(String birthday, String realName, String sex, String userid, LocalDateTime updateTime);
    userContentInfo getUserContentInfo(String userid);
    List<RechargeTrade> getOrder(String userid,String tradeModel);
    List<getContentInfo>  getUserContent (String userid);

//    List<SysUser> getUserLogin();

//    boolean addUser(int id);
//    test_demo findUser(int id);

}
