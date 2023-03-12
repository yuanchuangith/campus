package com.example.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.model.entity.Obedient;
import com.example.server.model.vo.conpany.conpanyRequstInfo;
import com.example.server.model.vo.obedient.getObedient;
import com.example.server.util.ResultUtil;

public interface obedientServe extends IService<Obedient> {
    ResultUtil getObedient(getObedient getObedient);
    ResultUtil getplate ( String ID , String key);
    ResultUtil getplateObedient(conpanyRequstInfo conpanyRequstInfo);
    ResultUtil getObedientDetial(conpanyRequstInfo conpanyRequstInfo);
}
