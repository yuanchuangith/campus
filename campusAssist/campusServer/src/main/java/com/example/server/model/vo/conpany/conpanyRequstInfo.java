package com.example.server.model.vo.conpany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class conpanyRequstInfo {
    private Integer model;
    private  int endPage;
    private  int startPage;
    private  String keyWord;
    private String contentId;

}
