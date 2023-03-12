package com.example.server.model.vo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class updatePW {

  private   String  userID;
  private   String Username;
    private   String  oldPassWord;
    private   String  newPassWord;
}
