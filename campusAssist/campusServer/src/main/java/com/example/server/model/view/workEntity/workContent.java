package com.example.server.model.view.workEntity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class workContent implements Serializable {

    String userID;
    String userName;
    String protraitPhoto;
    String contentID;
    String jobID;
    String content;
    Integer praiseNum;
    String tag;
    String wage;
    String companyName;
    String url;
    String textContent;


}
