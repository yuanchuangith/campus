package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Obedient;
import com.example.server.model.entity.Obedientclassification;
import com.example.server.model.view.conpany.seacherConpanyNmae;
import com.example.server.model.view.obedient.getObedientDetial;
import com.example.server.model.view.obedient.getReply;
import com.example.server.model.view.obenident;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface obedientMapper extends BaseMapper<Obedient> {


    List<obenident> getHatObenident(int strart,int end);
    List<Obedientclassification> getTopPlant ();
    List<seacherConpanyNmae> getindexObedient(String classificationID, String key );
    List<seacherConpanyNmae> getPlate(String classificationID,String key);
    List<obenident> getSearchObenident(String classificationID,int strart,int end);
    List<obenident> getSearchObenidentKey(String classificationID,String key,int strart,int end);
    getObedientDetial getObedientDetial(String obedientID);
    List<getReply> getReplys(String obedientID,int strat, int end);
}
