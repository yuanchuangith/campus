package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.conpanyMapper;

import com.example.server.model.entity.Position;
import com.example.server.model.entity.conpany;

import com.example.server.model.entity.conpanycontent;
import com.example.server.model.view.conpany.conpanContentCount;
import com.example.server.model.view.conpany.seacherConpanyNmae;
import com.example.server.model.vo.conpany.conpanyRequstInfo;
import com.example.server.model.vo.conpany.replyConpanyContent;
import com.example.server.service.conpanyServe;
import com.example.server.util.RedisCache;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Service
public class conpanyImpl extends ServiceImpl<conpanyMapper, conpany> implements conpanyServe {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private conpanyMapper conpanyMapper;

    @Autowired
    private com.example.server.mapper.conpanyContentMapper conpanyContentMapper;
    @Override
    public ResultUtil getConpanyAndRate( conpanyRequstInfo conpanyIndex) {
        if(Objects.isNull(conpanyIndex))
        {
            return null;
        }
        if(conpanyIndex.getModel()==1)
        {
            HashMap <String,Object  >  dataRedis= redisCache.getObjectClass("getConpanyIndex",HashMap.class);
            if(!Objects.isNull(dataRedis))
                return ResultUtil.success(200,"成功",dataRedis);
            HashMap<String,Object> data=new HashMap<>();

            ArrayList<conpany> conpanyAndRate = conpanyMapper.getConpanyAndRate(conpanyIndex.getStartPage(), conpanyIndex.getEndPage());
            int tatol=conpanyMapper.getConpantCount();
            data.put("conpany",conpanyAndRate);
            data.put("tatol",tatol);
            redisCache.setJSONString("getConpanyIndex", data, redisCache.redisTime);
            return ResultUtil.success(200,"成功",data);
        }
        else if(conpanyIndex.getModel()==2)
        {
            ArrayList<conpany> conpanyAndRate = conpanyMapper.getConpanyAndRate(conpanyIndex.getStartPage(), conpanyIndex.getEndPage());
            HashMap<String,Object> data=new HashMap<>();
            data.put("conpany",conpanyAndRate);
            return ResultUtil.success(200,"成功",data);
        }
        return null;
    }

    @Override
    public ResultUtil getSearchByconpanyName(conpanyRequstInfo conpanyIndex) {
        if(Objects.isNull(conpanyIndex) || conpanyIndex.getKeyWord()==null)
        {
            return null;
        }
        try {

            HashMap<String,Object> data=new HashMap<>();
            ArrayList<conpany> SearchConpanyAndRate = conpanyMapper.getSearchByconpanyName(conpanyIndex.getKeyWord(),conpanyIndex.getStartPage(), conpanyIndex.getEndPage());
            int count = conpanyMapper.getSearchCount(conpanyIndex.getKeyWord());
            data.put("conpany",SearchConpanyAndRate);
            data.put("tatol",count);


            return ResultUtil.success(200,"成功",data);
        }
        catch (Exception e)
        {
            return  ResultUtil.fail(400043,"失败");
        }

    }

    @Override
    public ResultUtil getConpanyDetail(conpanyRequstInfo conpanyRequstInfo) {

        try {
            if(Objects.isNull(conpanyRequstInfo) ||conpanyRequstInfo.getContentId()==null||conpanyRequstInfo.getContentId().length()<=10)
            {
                return null;
            }
            int a=conpanyRequstInfo.getModel();
            if(conpanyRequstInfo.getModel()==1)
            {
                HashMap <String,Object  >  dataRedis= redisCache.getObjectClass(conpanyRequstInfo.getContentId(),HashMap.class);
                if(!Objects.isNull(dataRedis))
                    return ResultUtil.success(200,"成功",dataRedis);
                conpany conpany = conpanyMapper.getConpanyInfo(conpanyRequstInfo.getContentId());
                ArrayList<Position> positions = conpanyMapper.getJobByConpanyId(conpanyRequstInfo.getContentId(),conpanyRequstInfo.getStartPage(),conpanyRequstInfo.getEndPage());
                ArrayList<conpanycontent> conpanycontents =conpanyMapper.getConpanyContentByConpanyID(conpanyRequstInfo.getContentId());
                conpanContentCount conpanContentCount =conpanyMapper.getConpanContentCount(conpanyRequstInfo.getContentId());
                HashMap<String,Object> data=new HashMap<>();
                data.put("positions",positions);
                data.put("conpanyInfo",conpany);
                data.put("conpanycontents",conpanycontents);
                data.put("conpanContentCount",conpanContentCount);
                redisCache.setJSONString(conpanyRequstInfo.getContentId(), data, redisCache.redisTime);

                return ResultUtil.success(200,"成功",data);
            }
            else {
                HashMap<String,Object> data=new HashMap<>();
                ArrayList<Position> positions = conpanyMapper.getJobByConpanyId(conpanyRequstInfo.getContentId(),conpanyRequstInfo.getStartPage(),conpanyRequstInfo.getEndPage());
                data.put("positions",positions);
                return ResultUtil.success(200,"成功",data);
            }
        }
        catch (Exception e)
        {
        }
             return null;

    }

    @Override
    public ResultUtil getconpantContentPage(conpanyRequstInfo conpanyRequstInfo) {
        if (Objects.isNull(conpanyRequstInfo))
        {
            return null;
        }
        if(conpanyRequstInfo.getContentId()==null||conpanyRequstInfo.getStartPage()<=0)
            return null;

        ArrayList<conpanycontent> conpanycontents =conpanyMapper.getConpanyContentByConpanyIDPage(conpanyRequstInfo.getContentId(),conpanyRequstInfo.getStartPage(),10);
          return ResultUtil.success(200,"成功",conpanycontents);
    }

    @Override
    public ResultUtil addPrais(String rateID,int index) {
        if(rateID==null) {
            return null;
        }
        conpanyMapper.addPraise(rateID);
        if(index<10)
        {

        }
        return ResultUtil.success(200,"成功","");

    }

    @Override
    public ResultUtil getSearchKeyByConpanyName(String key) {
     try {
         if (key==null)
             return  ResultUtil.fail(400041,"关键字为空");
         ArrayList<seacherConpanyNmae> searchByConpanyName = conpanyMapper.getTipsByConpanyName(key);
         return ResultUtil.success(200,"成功",searchByConpanyName);
     }
     catch (Exception e)
     {
        return  ResultUtil.fail(400042,"失败");
     }



    }

    @Override
    public ResultUtil replyContent(replyConpanyContent reply) {
        if(reply.getRate()==0||reply.getUserID()==null||reply.getUserID()==null)
            return null;
        conpanycontent conpanycontent =new conpanycontent();
        conpanycontent.setRateShow(1);
        conpanycontent.setDelTag(1);
        conpanycontent.setRateID(UUID.randomUUID().toString().replace("-",""));
        conpanycontent.setRate(reply.getRate());
        if(reply.getContent()!=null)
        conpanycontent.setContent(reply.getContent());
        conpanycontent.setCreateTime(LocalDateTime.now());
        conpanycontent.setUserID(reply.getUserID());
        conpanycontent.setUserName(reply.getUserName());
        conpanycontent.setPraiseNum(0);
        conpanycontent.setConpanyID(reply.getConpanyID());
        conpanyContentMapper.insert(conpanycontent);
        return ResultUtil.success(200,"ok",conpanycontent);
    }
}
