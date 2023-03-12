package com.example.server.service.threadTools;

import com.alibaba.fastjson.JSONObject;
import com.example.server.mapper.PositionMapper;
import com.example.server.mapper.workMapper;
import com.example.server.model.entity.Position;
import com.example.server.model.entity.Positioncontents;
import com.example.server.model.view.positionContent.getContentCount;
import com.example.server.model.view.positionContent.getPositionContents;
import com.example.server.model.view.positionContent.getReplyPositionContents;
import com.example.server.util.RedisCache;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class THUpdatePrise extends Thread {
    private  String jobID;
    private String contentID;
    private String replayID;
    private RedisCache redisCache;
    private workMapper workMapper;



    private PositionMapper PositionMapper;

    @SneakyThrows
    @Override
    public void run(){
        Thread.sleep(1000);
        System.out.println("updateRedisData执行");
        HashMap<String,Object  > dataRedis= redisCache.getObjectClass(this.jobID,HashMap.class);


        if(Objects.isNull(dataRedis)  ) {
            dataRedis=new HashMap<>();
            ArrayList<Position> positions = workMapper.getPositionDetail(this.jobID);
            dataRedis.put("positions", positions);
            ArrayList<getPositionContents> PositionContents = PositionMapper.getPositionContent(jobID, 0,4);
            ArrayList<getReplyPositionContents> ReplyPositionContents = PositionMapper.getGroupBycontentIdReply(jobID,0,4);
            ArrayList<getContentCount> ContentCounts = PositionMapper.getContentCount(this.jobID, 0,4);
            dataRedis.put("PositionContents", PositionContents);
            dataRedis.put("ReplyPositionContents", ReplyPositionContents);
            dataRedis.put("ContentCounts", ContentCounts);

            redisCache.setJSONString(this.jobID, dataRedis, redisCache.redisTime);
            return;

        }
        else
        {
            if(replayID==null&&contentID!=null)
            {
                List<getPositionContents> ContentCount=   JSONObject.parseArray(dataRedis.get("PositionContents").toString(),getPositionContents.class);
                dataRedis.remove("PositionContents");
                for (int i=0 ;i<ContentCount.size();i++)
                {
                    if(ContentCount.get(i).getContentID().equals(contentID))
                    {
                        ContentCount.get(i).setPraiseNum( ContentCount.get(i).getPraiseNum()+1);
                        break;
                    }
                }
                dataRedis.put("PositionContents",ContentCount);
            }
            else if(replayID!=null)
            {
                List<getReplyPositionContents> replyContent=   JSONObject.parseArray(dataRedis.get("ReplyPositionContents").toString(),getReplyPositionContents.class);

                dataRedis.remove("ReplyPositionContents");
                for (int i=0 ;i<replyContent.size();i++){
                    if(replyContent.get(i).getReplyID().equals(replayID))
                    {
                        replyContent.get(i).setReplypraiseNum(replyContent.get(i).getReplypraiseNum()+1);
                        break;
                    }
                }
                dataRedis.put("ReplyPositionContents",replyContent);
            }
        }
        redisCache.setJSONString(this.jobID, dataRedis, redisCache.redisTime);
    }

    public THUpdatePrise(String jobID, String contentID, String replayID, RedisCache redisCache, workMapper workMapper, PositionMapper positionMapper)
    {
        this.jobID=jobID;
        this.contentID=contentID;
        this.replayID=replayID;
        this.redisCache=redisCache;
        this.workMapper=workMapper;
        this.PositionMapper=positionMapper;
    }
}
