package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Position;
import com.example.server.model.entity.conpany;
import com.example.server.model.entity.conpanycontent;
import com.example.server.model.view.conpany.conpanContentCount;
import com.example.server.model.view.conpany.seacherConpanyNmae;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface conpanyMapper extends BaseMapper<conpany> {

    ArrayList<seacherConpanyNmae> getTipsByConpanyName(String key);
    ArrayList<conpany> getConpanyAndRate(int start,int end);
    int getConpantCount();
    ArrayList<conpany> getSearchByconpanyName(String keyword,int start,int end);
    int getSearchCount(String keyword);
    conpany getConpanyInfo(String conpanyID);
    ArrayList<Position> getJobByConpanyId(String conpanyID,int start,int end);
    ArrayList<conpanycontent> getConpanyContentByConpanyID(String conpanyID);
    conpanContentCount getConpanContentCount(String conpanyID);
    ArrayList<conpanycontent> getConpanyContentByConpanyIDPage(String conpanyID,int start,int end);
    int addPraise(String rateID);

}
