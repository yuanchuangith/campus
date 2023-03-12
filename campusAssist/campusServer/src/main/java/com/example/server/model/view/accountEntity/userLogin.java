package com.example.server.model.view.accountEntity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
public class userLogin implements Serializable {
    String username;
    String password;
    String code;
    boolean isKeep;
}
