package com.example.server.service.threadTools;

import com.alibaba.fastjson.JSONObject;
import com.example.server.mapper.workMapper;
import com.example.server.model.entity.Position;
import com.example.server.model.entity.Positioncontents;
import com.example.server.model.view.positionContent.getContentCount;
import com.example.server.model.view.positionContent.getPositionContents;
import com.example.server.model.view.positionContent.getReplyPositionContents;
import com.example.server.model.vo.Contents.replayContent;
import com.example.server.util.RedisCache;
import com.example.server.util.ResultUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.server.mapper.workMapper;
import com.example.server.mapper.PositionMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class updateRedisData extends Thread {

    private replayContent replayContent;

    private Positioncontents positioncontents;
    private workMapper workMapper;

    private RedisCache redisCache;

    private PositionMapper PositionMapper;
    @SneakyThrows
    @Override
    public void run(){
        Thread.sleep(1000);
        System.out.println("updateRedisData执行");
        HashMap<String,Object  > dataRedis= redisCache.getObjectClass(replayContent.getJobID(),HashMap.class);
        List<getContentCount> ContentCount=new ArrayList<>();

        if(Objects.isNull(dataRedis)  ) {
            dataRedis=new HashMap<>();
            ArrayList<Position> positions = workMapper.getPositionDetail(replayContent.getJobID());
            dataRedis.put("positions", positions);
            ArrayList<getPositionContents> PositionContents = PositionMapper.getPositionContent(replayContent.getJobID(), 0,4);
            ArrayList<getReplyPositionContents> ReplyPositionContents = PositionMapper.getGroupBycontentIdReply(replayContent.getJobID(),0,4);
            ArrayList<getContentCount> ContentCounts = PositionMapper.getContentCount(replayContent.getJobID(), 0,4);
            dataRedis.put("PositionContents", PositionContents);
            dataRedis.put("ReplyPositionContents", ReplyPositionContents);
      dataRedis.put("ContentCounts", ContentCounts);

            redisCache.setJSONString(replayContent.getJobID(), dataRedis, redisCache.redisTime);
            return;
    
        }
        else 
        {
            ContentCount = JSONObject.parseArray(dataRedis.get("ContentCounts").toString(),getContentCount.class);





        }
        if(replayContent.getReplayID()==null && replayContent.getContentID()==null)
        {
           for(int i=0 ;i<ContentCount.size();i++)
           {
               getContentCount temp=ContentCount.get(i);
               ContentCount.get(i).setPositioncontentCount(temp.getPositioncontentCount()+1);



           }
           //把新的评论添加到缓存
//           if(!Objects.isNull(positioncontents)) {
//               List<getPositionContents> PositionContent=JSONObject.parseArray(dataRedis.get("PositionContents").toString(),getPositionContents.class);
//               dataRedis.remove("ContentCounts");
//               getPositionContents getPositionContents = new getPositionContents();
//               getPositionContents.setContent(positioncontents.getContent());
//               getPositionContents.setContentID(positioncontents.getContentID());
//               getPositionContents.setCreateTime(positioncontents.getCreateTime().toString());
//               getPositionContents.setPraiseNum(0);
//               getPositionContents.setImages(positioncontents.getImages());
//               getPositionContents.setUserID(replayContent.getUserID());
//               getPositionContents.setUserName(replayContent.getUserName());
//               PositionContent.add(getPositionContents);
//               PositionContent.add(getPositionContents);
//               dataRedis.remove("PositionContents");
//               dataRedis.put("PositionContents",PositionContent);
//           }
            dataRedis.put("ContentCounts", ContentCount);
        }
        else 
        {
            for(int i=0 ;i<ContentCount.size();i++)
            {

                if(replayContent.getContentID().equals(ContentCount.get(i).getContentID()))
                {
                    ContentCount.get(i).setGroupCount(ContentCount.get(i).getGroupCount()+1);

                    break;
                }


            }
            dataRedis.put("ContentCounts", ContentCount);

        }
        redisCache.setJSONString(replayContent.getJobID(), dataRedis, redisCache.redisTime);
    }

    public  updateRedisData(replayContent replayContent, workMapper workMapper,PositionMapper positionMapper,RedisCache redisCache,Positioncontents positioncontents){
        this.replayContent=replayContent;
        this.workMapper=workMapper;
        this.PositionMapper=positionMapper;
        this.redisCache=redisCache;
        this.positioncontents=positioncontents;
    }
}
