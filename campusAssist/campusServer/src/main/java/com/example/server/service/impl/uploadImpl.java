package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.userMapper;
import com.example.server.model.LoginUser;
import com.example.server.model.entity.User;
import com.example.server.service.uploadServe;
import com.example.server.util.RedisCache;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Service
public class uploadImpl  extends ServiceImpl<userMapper, User> implements uploadServe {
    @Value("${prop.upload-imgfolder}")
    private String uploadimgfolder;
    @Autowired
    private userMapper userMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResultUtil uploadavatar(MultipartFile file, String userID) {
        if (Objects.isNull(file) ) {
            return ResultUtil.fail(0, "请选择要上传的图片");
        }
        LoginUser loginUser=redisCache.getObjectClass("loginuser"+userID,LoginUser.class);
        if (Objects.isNull(loginUser) ) {
            return ResultUtil.fail(4001, "请登录或者登录过期");
        }

        if (file.getContentType()!="image/png"||file.getContentType()=="image/jpg"||file.getContentType()=="image/jpge")
            try{

                //路径方式：1、绝对路径
                String tempPath=uploadimgfolder+"/"+userID+"/";


                System.out.println("存放路径："+uploadimgfolder);
                File folder = new File(uploadimgfolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                String oldName = file.getOriginalFilename();
                String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                file.transferTo(new File(folder,newName));


                //文件名称
                String fileName=tempPath+"/"+newName;
                userMapper.updateAvatar(newName,userID);
                System.out.println("文件名称："+fileName+"---文件大小"+(file.getSize()/1024));
                loginUser.getUser().setProtraitPhoto(newName);
                redisCache.setJSONString("loginuser"+loginUser.getUser().getUserID(),loginUser,redisCache.userLoginTime);
                return ResultUtil.success(200, "上传成功",null);

            }catch (Exception e){
                return ResultUtil.fail(40002, "上传失败",null);
            }
        return null;
    }
}
