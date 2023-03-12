package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.obedientMapper;
import com.example.server.model.entity.Obedient;
import com.example.server.model.entity.Obedientclassification;
import com.example.server.model.view.conpany.seacherConpanyNmae;
import com.example.server.model.view.obedient.getObedientDetial;
import com.example.server.model.view.obedient.getReply;
import com.example.server.model.view.obenident;
import com.example.server.model.vo.conpany.conpanyRequstInfo;
import com.example.server.model.vo.obedient.getObedient;
import com.example.server.service.obedientServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class obedientImpl  extends ServiceImpl<obedientMapper, Obedient> implements obedientServe {

    @Autowired
    private obedientMapper obedientMapper;


    @Override
    public ResultUtil getObedient(getObedient getObedient) {
        if(Objects.isNull(getObedient))
        {
            return ResultUtil.fail(404,"数据格式错误");
        }
        HashMap<String,Object  > data= new HashMap<>();

        if(getObedient.getModel()==1) {
            List<obenident> hatObenident = obedientMapper.getHatObenident(getObedient.getStrart(), getObedient.getEnd());
            List<Obedientclassification> topPlant = obedientMapper.getTopPlant();
            data.put("hatObenident",hatObenident);
            data.put("topPlant",topPlant);


        }
        else if(getObedient.getModel()==2)
        {
            List<obenident> hatObenident = obedientMapper.getHatObenident(getObedient.getStrart(), getObedient.getEnd());

            data.put("hatObenident",hatObenident);
            data.put("topPlant",null);
        }

        return ResultUtil.success(200,"成功",data);
    }

    @Override
    public ResultUtil getplate(String ID ,String key) {
        if(ID==null||key==null)
        {
            return ResultUtil.fail(404,"数据格式错误");
        }

        List<seacherConpanyNmae> plate = obedientMapper.getPlate(ID, key);
        return ResultUtil.success(200,"成功",plate);
    }

    @Override
    public ResultUtil getplateObedient(conpanyRequstInfo conpanyRequstInfo) {

        if(Objects.isNull((conpanyRequstInfo)))
        {
            return ResultUtil.fail(404,"数据格式错误");
        }
        if(conpanyRequstInfo.getKeyWord()==null || conpanyRequstInfo.getKeyWord().equals(""))
        {
            List<obenident> hatObenident = obedientMapper.getSearchObenident(conpanyRequstInfo.getContentId(),conpanyRequstInfo.getStartPage(), conpanyRequstInfo.getEndPage());
            return ResultUtil.success(200,"成功",hatObenident);
        }
        else
        {
            List<obenident> hatObenident = obedientMapper.getSearchObenidentKey(conpanyRequstInfo.getContentId(),conpanyRequstInfo.getKeyWord(),conpanyRequstInfo.getStartPage(), conpanyRequstInfo.getEndPage());
            return ResultUtil.success(200,"成功",hatObenident);
        }

    }

    @Override
    public ResultUtil getObedientDetial(conpanyRequstInfo conpanyRequstInfo) {
        if(Objects.isNull(conpanyRequstInfo))
        {
            return ResultUtil.fail(404,"数据格式错误");
        }
        HashMap<String,Object  > data= new HashMap<>();
        if(conpanyRequstInfo.getModel()==1)
        {
            getObedientDetial obedientDetial = obedientMapper.getObedientDetial(conpanyRequstInfo.getContentId());
            List<getReply> replys = obedientMapper.getReplys(conpanyRequstInfo.getContentId(), conpanyRequstInfo.getStartPage(), conpanyRequstInfo.getEndPage());
            data.put("obedientDetial",obedientDetial);
            data.put("replys",replys);
            data.put("model",conpanyRequstInfo.getModel());

            return ResultUtil.success(200,"成功",data);


        }
        else if(conpanyRequstInfo.getModel()==2) {
            List<getReply> replys = obedientMapper.getReplys(conpanyRequstInfo.getContentId(), conpanyRequstInfo.getStartPage(), conpanyRequstInfo.getEndPage());
            data.put("replys",replys);
            data.put("model",conpanyRequstInfo.getModel());
            return ResultUtil.success(200,"成功",data);
        }
        return ResultUtil.fail(404,"数据格式错误");
    }
}
