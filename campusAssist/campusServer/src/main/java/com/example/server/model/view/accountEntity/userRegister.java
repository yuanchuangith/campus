package com.example.server.model.view.accountEntity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
public class userRegister implements Serializable {
    private String userID;

    private String userName;

    private String passWord;
    private String repeatPassWord;

    private String realName;

    private String protraitPhoto;

    private LocalDateTime birthday;

    private Integer sex;
    private String loginIp;

    private String telephone;
    private String createBy;
    private  String code;

}
