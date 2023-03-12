package com.example.server.model.vo.workEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getJobInfoAndContent {
    private String jobID;
    private int startpage;
    private int endpage;
    private int model;
}
