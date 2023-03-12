package com.example.server.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.example.server.mapper.PositionContentsMapper;
import com.example.server.mapper.ReplypositioncontentMapper;
import com.example.server.mapper.workMapper;

import com.example.server.model.entity.Position;
import com.example.server.model.entity.Positioncontents;
import com.example.server.model.entity.Provicegroupinfo;
import com.example.server.model.entity.Replypositioncontent;
import com.example.server.model.view.positionContent.getContentCount;
import com.example.server.model.view.positionContent.getPositionContents;
import com.example.server.model.view.positionContent.getReplyPositionContents;
import com.example.server.model.view.workEntity.*;
import com.example.server.model.vo.Contents.getReplyContent;
import com.example.server.model.vo.Contents.replayContent;
import com.example.server.model.vo.workEntity.getJobInfoAndContent;
import com.example.server.model.vo.workEntity.getPositionEntity;
import com.example.server.model.vo.workEntity.searchContent;
import com.example.server.service.jobServe;
import com.example.server.service.threadTools.updateRedisData;
import com.example.server.util.RedisCache;
import com.example.server.util.ResultUtil;
//import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public  class jobImpl extends ServiceImpl<workMapper, Position> implements jobServe {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ReplypositioncontentMapper replypositioncontentMapper;
    @Autowired
    private PositionContentsMapper positionontentsMapper;

    @Autowired
    private com.example.server.mapper.workMapper workMapper;

    @Autowired
    private com.example.server.mapper.PositionMapper PositionMapper;

    @Override
    public ResultUtil getProvices() {

//        redisCache.delete("provicesums");
        List<Object> proviceInfos= redisCache.getObjectClass("provicesums",List.class);
        if(!Objects.isNull(proviceInfos)) {

            List<proviceInfo> fileInfos = proviceInfos.stream()
                .map(entity -> JSONUtil.toBean(JSONUtil.parseObj(entity), proviceInfo.class))
                .collect(Collectors.toList());
            return ResultUtil.success(200, "sucess", fileInfos);
        }
        else {
            Provicegroupinfo provicegroupinfo = workMapper.getProviceId();
            List<proviceInfo> provicesums = workMapper.getProviceInfo(provicegroupinfo.getProviceInfoID());

            redisCache.setJSONString("provicesums",provicesums,redisCache.redisTime);
            return ResultUtil.success(200, "sucess", provicesums);
        }
    }

    @Override
    public ResultUtil getJobRequierInfo() {
//        redisCache.delete("educationRequire");

        List<Object> educationRequire= redisCache.getObjectClass("educationRequire",List.class);
        List<educationRequire> requires=null;
        if(!Objects.isNull(educationRequire)) {
             requires =educationRequire .stream()
                    .map(entity -> JSONUtil.toBean(JSONUtil.parseObj(entity), educationRequire.class))
                    .collect(Collectors.toList());
        }
        else
        {
            requires   = workMapper.geteducationRequires();
            redisCache.setJSONString("educationRequire",requires,redisCache.redisTime);
        }

        int TotoSum=0;
        for (int i=0 ;i<requires.size();i++)
        {
            TotoSum+=requires.get(i).getCompeducationRequireSum();
        }
        Map<String,Object> data=new HashMap<>();
        data.put("TotoSum",TotoSum);
        data.put("educationRequire",requires);
        return ResultUtil.success(200, "成功",data);
    }

    @Override
    public ResultUtil getPosition() {
//        redisCache.delete("positions");
        List<Object> positionsRedis= redisCache.getObjectClass("positions",List.class);
        if(!Objects.isNull(positionsRedis)) {
            List<Position> positions=positionsRedis.stream().map(item->JSONUtil.toBean(JSONUtil.parseObj(item),Position.class))
                    .collect(Collectors.toList());
            return ResultUtil.success(200, "成功",positions);
        }
        else
        {
            List<Position> positions = workMapper.getPostionS();
            redisCache.setJSONString("positions",positions,redisCache.redisTime);
            return ResultUtil.success(200, "成功",positions);
        }

    }

    @Override
    public ResultUtil getWorkContent() {
        List<Object> positionsRedis= redisCache.getObjectClass("workContents",List.class);
//        workContent
        if(!Objects.isNull(positionsRedis)) {
            List<workContent> workContents=positionsRedis.stream().map(item->JSONUtil.toBean(JSONUtil.parseObj(item), workContent.class))
                    .collect(Collectors.toList());
            return ResultUtil.success(200, "成功",workContents);
        }
        else
        {
            List<workContent> workContents = workMapper.getContent();
            redisCache.setJSONString("workContents",workContents,redisCache.redisTime);
            return ResultUtil.success(200, "成功",workContents);
        }
    }

    @Override
    public ResultUtil getNewWorkContent() {
        List<workContent> workContents = workMapper.getContent();
        return ResultUtil.success(200, "成功",workContents);
    }

    @Override
    public ResultUtil getPosiyionNumRank() {
        List<Object> PositionNumRedis= redisCache.getObjectClass("getPosiyionNumRank",List.class);
        List<Object> positionBigRedis= redisCache.getObjectClass("positionBigClass",List.class);
        if(!Objects.isNull(PositionNumRedis) &&!Objects.isNull(positionBigRedis) ) {

            List<positionNumRank> positionNumRanks=PositionNumRedis.stream().map(item->JSONUtil.toBean(JSONUtil.parseObj(item), positionNumRank.class))
                    .collect(Collectors.toList());
            List<positionBigClass> positionBigClasss=positionBigRedis.stream().map(item->JSONUtil.toBean(JSONUtil.parseObj(item), positionBigClass.class))
                    .collect(Collectors.toList());
            HashMap<String,Object> data = new HashMap<>();
            data.put("positionNumRanks",positionNumRanks);
            data.put("positionBigClass",positionBigClasss);
            return ResultUtil.success(200, "成功",data);
        }
        else
        {
            List<positionNumRank> positionNumRanks = workMapper.getpositionNumRank();
            List<positionBigClass>positionBigClasss=workMapper.getpositionBigNum();
            redisCache.setJSONString("getPosiyionNumRank",positionNumRanks,redisCache.redisTime);
            redisCache.setJSONString("positionBigClass",positionBigClasss,redisCache.redisTime);
            HashMap<String,Object> data = new HashMap<>();
            data.put("positionNumRanks",positionNumRanks);
            data.put("positionBigClass",positionBigClasss);
            return ResultUtil.success(200, "成功",data);
        }

    }

    @Override
    public ResultUtil getWorkProvices() {
        List<Object> provicesNumRedis= redisCache.getObjectClass("getWorkProvices",List.class);
        if(!Objects.isNull(provicesNumRedis)  ) {
                List<provice> provices = provicesNumRedis.stream().map(item->JSONUtil.toBean(JSONUtil.parseObj(item),provice.class)).collect(Collectors.toList());
                return  ResultUtil.success(200,"成功",provices);
        }
        else
        {
            List<provice> provices=workMapper.getprovices();
            redisCache.setJSONString("getWorkProvices",provices,redisCache.redisTime);
            return  ResultUtil.success(200,"成功",provices);

        }

    }

    @Override
    public ResultUtil getIndexPosition(getPositionEntity entity) {

        if(entity.getStartPage()%40!=0 ||entity.getEndPage()%40!=0||entity.getStartPage()<0||entity.getEndPage()!=40)
            return ResultUtil.fail(404,"数据格式错误");
        HashMap <String,Object  > data= new HashMap<>();
        if (entity.getProvice()!=null && entity.getIndustry()==null)
        {


            ArrayList<positionSimply> positionSimplies=workMapper.selectPositionByProvice(entity.getProvice(),entity.getStartPage(),entity.getEndPage());

            positionTotal  positionTotal= redisCache.getObjectClass(entity.getProvice(),positionTotal.class);
            if(Objects.isNull(positionTotal)  ) {
                positionTotal=workMapper.getPositionProviceTotal(entity.getProvice());
                redisCache.setJSONString(entity.getProvice(),positionTotal,redisCache.redisLongTime);
            }



            data.put("positionSimplies",positionSimplies);
            data.put("positionTotal",positionTotal.total);

            return ResultUtil.success(200,"成功",data);
        }
        else if(entity.getIndustry()!=null){
            if(entity.getProvice()!=null)
            {
                ArrayList<positionSimply> positionSimplies=workMapper.selectPositionByProviceandIndustryID(entity.getProvice(),entity.getIndustry(),entity.getStartPage(),entity.getEndPage());
                positionTotal  positionTotal= redisCache.getObjectClass(entity.getProvice()+entity.getIndustry(),positionTotal.class);
                if(Objects.isNull(positionTotal)  ) {
                    positionTotal=workMapper.getPositionIndustryIDAndProviceTotal(entity.getProvice(),entity.getIndustry());
                    redisCache.setJSONString(entity.getProvice()+entity.getIndustry(),positionTotal,redisCache.redisLongTime);
                }
                data.put("positionSimplies",positionSimplies);
                data.put("positionTotal",positionTotal.total);

                return ResultUtil.success(200,"成功",data);
            }
            else {
                ArrayList<positionSimply> positionSimplies=workMapper.selectPositionByIndustryID(entity.getIndustry(),entity.getStartPage(),entity.getEndPage());
                positionTotal  positionTotal= redisCache.getObjectClass(entity.getIndustry(),positionTotal.class);
                if(Objects.isNull(positionTotal)  ) {
                    positionTotal=workMapper.getPositionIndustryTotal(entity.getIndustry());
                    redisCache.setJSONString(entity.getIndustry(),positionTotal,redisCache.redisLongTime);
                }
                data.put("positionSimplies",positionSimplies);
                data.put("positionTotal",positionTotal.total);

                return ResultUtil.success(200,"成功",data);
            }

        }
        else {
            ArrayList<positionSimply> positionSimplies=workMapper.selectPosition(entity.getStartPage(),entity.getEndPage());
            positionTotal  positionTotal= redisCache.getObjectClass("positionTotal",positionTotal.class);
            if(Objects.isNull(positionTotal)  ) {
                 positionTotal = workMapper.getPositionTotal();
                redisCache.setJSONString("positionTotal",positionTotal,redisCache.redisLongTime);
            }

            data.put("positionSimplies",positionSimplies);
            data.put("positionTotal",positionTotal.total);

            return ResultUtil.success(200,"成功",data);
        }

    }

    @Override
    public ResultUtil getIndustrykind() {
        HashMap <String,Object  >  dataRedis= redisCache.getObjectClass("IndustryKind",HashMap.class);
        if(Objects.isNull(dataRedis)  ) {
            ArrayList<classificationEntity> big = workMapper.getPositionbigclassifications();
            ArrayList<classificationEntity> middle = workMapper.getPositionmiddleclassifications();
            ArrayList<classificationEntity> samll = workMapper.getPositionclassifications();
            HashMap<String, Object> data = new HashMap<>();
            data.put("big", big);
            data.put("middle", middle);
            data.put("samll", samll);
            redisCache.setJSONString("IndustryKind", data, redisCache.redisLongTime);
            return ResultUtil.success(200,"成功",data);
        }
        else
        {
            return ResultUtil.success(200,"成功",dataRedis);
        }



    }

    @Override
    public ResultUtil getSearchContent(searchContent searchContent) {

        if(searchContent.getChangeKeyKind())
        {
            ArrayList<searchKey> searchKeys=workMapper.searchByTagNmae(searchContent.getKey());
            if(searchKeys.size()>2)
            {
                return ResultUtil.success(200,"成功",searchKeys);
            }else
            {
                String key=searchContent.getKey()+"%";
                searchKeys=workMapper.searchByLikeTagNmae(key);
                return ResultUtil.success(200,"成功",searchKeys);

            }
        }
        else
        {
            ArrayList<searchKey> searchKeys=workMapper.searchByConpanyNmae(searchContent.getKey());
            if(searchKeys.size()>2)
            {
                return ResultUtil.success(200,"成功",searchKeys);
            }
            else {
                String key=searchContent.getKey()+"%";
                searchKeys=workMapper.searchByLikeConpanyNmae(key);
                return ResultUtil.success(200,"成功",searchKeys);
            }


        }

    }

    @Override
    public ResultUtil getIndexSearchPosition(getPositionEntity entity) {
        if(entity.getStartPage()%40!=0 ||entity.getEndPage()%40!=0||entity.getStartPage()<0||entity.getEndPage()!=40)
            return ResultUtil.fail(404,"数据格式错误");
        if(entity.getKeyWord()!=null)
        {
            HashMap <String,Object  > data= new HashMap<>();
            if(entity.getProvice()!=null)
            {
                if(entity.isDibIsChange())
                {
                    ArrayList<positionSimply> positionSimplies =workMapper.getSearchByProviceNameAndTagPosition(entity.getKeyWord(),entity.getProvice(),entity.getStartPage(),entity.getEndPage());
                    if(positionSimplies.size()>2){

                        positionTotal  positionTotal=workMapper.getSearchTatolByTag(entity.getKeyWord(),entity.getProvice(),0,1);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }

                    else
                    {
                        positionSimplies=  workMapper.getSearchByProviceNameAndLikeTagPosition(entity.getKeyWord(),entity.getProvice(),entity.getStartPage(),entity.getEndPage());
                        positionTotal  positionTotal=workMapper.getSearchTatolByTag(entity.getKeyWord(),entity.getProvice(),1,1);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }
                }
                else
                {
                    ArrayList<positionSimply> positionSimplies =workMapper.getSearchByconpanyNameAndProviceNamePosition(entity.getKeyWord(),entity.getProvice(),entity.getStartPage(),entity.getEndPage());
                    if(positionSimplies.size()>2){

                        positionTotal  positionTotal=workMapper.getSearchTatolByConpany(entity.getKeyWord(),entity.getProvice(),0,1);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }

                    else
                    {
                        positionSimplies=  workMapper.getSearchByLikeConpanyNamePositionOrProvice(entity.getKeyWord(),entity.getProvice(),entity.getStartPage(),entity.getEndPage());
                        positionTotal  positionTotal=workMapper.getSearchTatolByConpany(entity.getKeyWord(),entity.getProvice(),1,1);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }
                }
            }
            else
            {
                if(entity.isDibIsChange())
                {
                    ArrayList<positionSimply> positionSimplies =workMapper.getSearchByTagPosition(entity.getKeyWord(),entity.getStartPage(),entity.getEndPage());
                    if(positionSimplies.size()>2){

                        positionTotal  positionTotal=workMapper.getSearchTatolByTag(entity.getKeyWord(),entity.getProvice(),0,0);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }

                    else
                    {
                        positionSimplies=  workMapper.getSearchByLikeTagPosition(entity.getKeyWord(),entity.getStartPage(),entity.getEndPage());
                        positionTotal  positionTotal=workMapper.getSearchTatolByTag(entity.getKeyWord(),entity.getProvice(),1,0);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }
                }
                else
                {
                    ArrayList<positionSimply> positionSimplies =workMapper.getSearchByconpanyNamePosition(entity.getKeyWord(),entity.getStartPage(),entity.getEndPage());
                    if(positionSimplies.size()>2){

                        positionTotal  positionTotal=workMapper.getSearchTatolByConpany(entity.getKeyWord(),entity.getProvice(),0,0);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }

                    else
                    {
                        positionSimplies=  workMapper.getSearchByLikeTagPosition(entity.getKeyWord(),entity.getStartPage(),entity.getEndPage());
                        positionTotal  positionTotal=workMapper.getSearchTatolByConpany(entity.getKeyWord(),entity.getProvice(),1,0);
                        data.put("positionSimplies",positionSimplies);
                        data.put("positionTotal",positionTotal.total);

                        return ResultUtil.success(200,"成功",data);
                    }
                }
            }
        }
        return ResultUtil.success(400021,"搜索内容为空",null);
    }



    @Override
    public ResultUtil getPositionDetail(getJobInfoAndContent getJobInfoAndContent) {

        if(getJobInfoAndContent.getJobID()==null)
        {
            return ResultUtil.success(400026,"参数错误",null);
        }
        HashMap <String,Object  > data= new HashMap<>();
        if (getJobInfoAndContent.getModel()==1) {
//            HashMap <String,Object  >  dataRedis= redisCache.getObjectClass(getJobInfoAndContent.getJobID(),HashMap.class);
//            if(Objects.isNull(dataRedis)  ) {
                ArrayList<Position> positions = workMapper.getPositionDetail(getJobInfoAndContent.getJobID());
                data.put("positions", positions);
                ArrayList<getPositionContents> PositionContents = PositionMapper.getPositionContent(getJobInfoAndContent.getJobID(), getJobInfoAndContent.getStartpage(), getJobInfoAndContent.getEndpage());
                ArrayList<getReplyPositionContents> ReplyPositionContents = PositionMapper.getGroupBycontentIdReply(getJobInfoAndContent.getJobID(), getJobInfoAndContent.getStartpage(), getJobInfoAndContent.getEndpage());
                ArrayList<getContentCount> ContentCounts = PositionMapper.getContentCount(getJobInfoAndContent.getJobID(), getJobInfoAndContent.getStartpage(), getJobInfoAndContent.getEndpage());
                data.put("PositionContents", PositionContents);
                data.put("ReplyPositionContents", ReplyPositionContents);
                data.put("ContentCounts", ContentCounts);



                return ResultUtil.success(200, "成功", data);
//            }
//            else
//                return ResultUtil.success(200, "成功", dataRedis);



        }
        else
        {
            ArrayList<getPositionContents> PositionContents = PositionMapper.getPositionContent(getJobInfoAndContent.getJobID(), getJobInfoAndContent.getStartpage(), getJobInfoAndContent.getEndpage());
            ArrayList<getReplyPositionContents> ReplyPositionContents= PositionMapper.getGroupBycontentIdReply(getJobInfoAndContent.getJobID(),getJobInfoAndContent.getStartpage(), getJobInfoAndContent.getEndpage());
            ArrayList<getContentCount> ContentCounts = PositionMapper.getContentCount(getJobInfoAndContent.getJobID(), getJobInfoAndContent.getStartpage(), getJobInfoAndContent.getEndpage());
            data.put("PositionContents",PositionContents);
            data.put("ReplyPositionContents",ReplyPositionContents);
            data.put("ContentCounts",ContentCounts);
            return ResultUtil.success(200,"成功",data);
        }

    }
    @Override
    public ResultUtil getReplyContent(getReplyContent getReplyContent) {
        if (getReplyContent.getContentID()==null)
            return ResultUtil.fail(400027,"缺少参数");
        ArrayList<getReplyPositionContents>  getReplyPositionContents= PositionMapper.getcontentIdReply(getReplyContent.getContentID(),getReplyContent.getStartpage(),getReplyContent.getEndpage());
        HashMap <String,Object  > data= new HashMap<>();
        data.put("getReplyPositionContents",getReplyPositionContents);
        return ResultUtil.success(200,"成功",data);
    }




    @Override
    public ResultUtil saveContentReply(replayContent replayContent) {
        if (replayContent.getContentData()==null||replayContent.getJobID()==null||replayContent.getUserID()==null)
            return ResultUtil.fail(400031,"参数错误");
        HashMap <String,Object  > data=new HashMap<>();


        if(replayContent.getContentID()!=null && replayContent.getContentID()!=""&& !replayContent.getContentID().equals(""))
        {
//            ArrayList<getContentCount> getContentCounts = (ArrayList<getContentCount>) dataRedis.get("ContentCounts");
            Replypositioncontent replypositioncontent=new Replypositioncontent();
            replypositioncontent.setUserID(replayContent.getUserID());
            if(replayContent.getReplayID()!=null && replayContent.getReplayID()!=""&& !replayContent.getReplayID().equals(""))
            {


                replypositioncontent.setContentID(replayContent.getContentID());
                replypositioncontent.setReplyID(UUID.randomUUID().toString().replace("-",""));
                replypositioncontent.setContent(replayContent.getContentData());
                replypositioncontent.setContentshow(1);
                replypositioncontent.setDelTag(1);
                replypositioncontent.setCreateTime(LocalDateTime.now());
                replypositioncontent.setImages(replayContent.getStrimgPath());
                replypositioncontent.setPraiseNum(0);
                replypositioncontent.setReplyToPerson(replayContent.getReplayName());


            int result=   replypositioncontentMapper.insert(replypositioncontent);
            if(result>0)
            {
                updateRedisData updateRedisData=new updateRedisData(replayContent,workMapper,PositionMapper,redisCache,null);
                updateRedisData.start();
                data.put("model",2);
                data.put("content",replypositioncontent);
                return ResultUtil.success(200,"成功",data);
            }
            else
                return ResultUtil.fail(400033,"失败");

            }
            else {


                replypositioncontent.setContentID(replayContent.getContentID());
                replypositioncontent.setReplyID(UUID.randomUUID().toString().replace("-",""));
                replypositioncontent.setContent(replayContent.getContentData());
                replypositioncontent.setContentshow(1);
                replypositioncontent.setDelTag(1);
                replypositioncontent.setCreateTime(LocalDateTime.now());
                replypositioncontent.setImages(replayContent.getStrimgPath());
                replypositioncontent.setPraiseNum(0);


                replypositioncontentMapper.insert(replypositioncontent);
                data.put("model",2);
                data.put("content",replypositioncontent);
//                updateRedisData updateRedisData=new updateRedisData(replayContent,workMapper,PositionMapper,redisCache,null);
//                updateRedisData.start();
                return ResultUtil.success(200,"成功",data);
            }

        }
        else{
            Positioncontents positioncontents = new Positioncontents();
            positioncontents.setContentID(UUID.randomUUID().toString().replace("-",""));
            positioncontents.setContent(replayContent.getContentData());
            positioncontents.setCreateTime(LocalDateTime.now());
            positioncontents.setDelTag(1);
            positioncontents.setImages(replayContent.getStrimgPath());
            positioncontents.setContentshow(1);
            positioncontents.setUserID(replayContent.getUserID());
            positioncontents.setJobID(replayContent.getJobID());
            positioncontents.setPraiseNum(0);

            positionontentsMapper.insert(positioncontents);
            data.put("model",1);
            data.put("content",positioncontents);
//            updateRedisData updateRedisData=new updateRedisData(replayContent,workMapper,PositionMapper,redisCache,positioncontents);
//            updateRedisData.start();
            return ResultUtil.success(200,"成功",data);

        }

    }

    @Override
    public ResultUtil addPrise(String contentID, String replayID,int model,String jobID) {
        int result=0;
        if(replayID==null&&contentID!=null)
        {
            result=  positionontentsMapper.addPriase(contentID);
        }
        else if(replayID!=null)
            result= replypositioncontentMapper.addPriase(replayID);
//        if (model<4 && jobID !=null)
//        {
//            THUpdatePrise thUpdatePrise = new THUpdatePrise(jobID,contentID,replayID,redisCache,workMapper,PositionMapper);
//            thUpdatePrise.start();
//        }
        return ResultUtil.success(200,"成功",result);

    }


}
