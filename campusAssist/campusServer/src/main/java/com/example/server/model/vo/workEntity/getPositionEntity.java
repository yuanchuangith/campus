package com.example.server.model.vo.workEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getPositionEntity {
   private String provice;
    private String  industry;
    private  int endPage;
    private  int startPage;
    private String keyWord;
    private boolean dibIsChange;
}
