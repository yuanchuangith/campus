package com.example.server.controller;

import com.example.server.service.uploadServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/upload")
public class uploadController {

    private SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    DecimalFormat df = new DecimalFormat("######0.00");
    @Value("${prop.upload-folder}")
    private String uploadFolder;

    @Autowired
    private uploadServe uploadServer;

    @PostMapping("/uploadImg")
    public ResultUtil upload(@RequestParam(name="files",required = false)  MultipartFile[] files,@RequestParam(name="contentID",required = false)  String contentID,
                             @RequestParam(name="replayID",required = false)  String replayID,
                             @RequestParam(name="replayName",required = false)  String replayName, HttpServletRequest request) {


        if (replayName==null && replayName=="" ) {
                return ResultUtil.fail(0, "请登录");
        }
        if (Objects.isNull(files) ) {
            return ResultUtil.fail(0, "请选择要上传的图片");
        }
        ArrayList<String> pathImg=new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getContentType()!="image/png"||file.getContentType()=="image/jpg"||file.getContentType()=="image/jpge")
            try{
                String format = sdf.format(new Date());
                //路径方式：1、绝对路径
                String tempPath=replayName+"/";

                tempPath=tempPath+contentID;
                if(replayID!=null && replayID!="")
                    tempPath=tempPath+"/"+replayID+"/";
                String realPath = uploadFolder+tempPath;
                System.out.println("存放路径："+realPath);
                File folder = new File(realPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                String oldName = file.getOriginalFilename();
                String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                file.transferTo(new File(folder,newName));
                pathImg.add(tempPath+"/"+newName);

                //文件名称
                String fileName=tempPath+"/"+newName;
                //文件大小
                String fileSize = df.format((file.getSize()/1024))+"kb";
                System.out.println("文件名称："+fileName+"---文件大小"+fileSize);
            }catch (Exception e){
                return null;
            }
        }
        return ResultUtil.success(200,"成功",pathImg);


    }
    @PostMapping("/avatar")
    public ResultUtil upload(@RequestParam(name="file",required = false)  MultipartFile file,@RequestParam(name="userID",required = false)  String userID ){

    return uploadServer.uploadavatar(file,userID);
    }

}
