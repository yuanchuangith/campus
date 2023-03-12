package com.example.server.controller;



import com.example.server.mapper.userMapper;
import com.example.server.model.view.accountEntity.userLogin;
import com.example.server.model.view.accountEntity.userRegister;
import com.example.server.model.entity.User;
import com.example.server.model.vo.user.updatePW;
import com.example.server.model.vo.user.updateUserInfo;
import com.example.server.service.userServe;
import com.example.server.util.ResultUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Account")
public class AccountController {

//    @Autowired(required = false)
//   JdbcTemplate jdbcTemplate;
    @Autowired
    private userServe loginServers;

    @Autowired
    com.example.server.mapper.userMapper userMapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
   public AccountController(userMapper userMapper )
   {
       this.userMapper=userMapper;
   }
    @RequestMapping("/index")

    public   Map<String, String[]>  hello(HttpServletRequest request){
//         String sql = "select * from test_demo";

        Map<String, String[]> requestParams = request.getParameterMap();

        return requestParams;
    }
    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority ('test','admin')")
    public List<User> test(){
//         String sql = "select * from test_demo";
        List<User> temp = userMapper.querUserList();


        return  temp;
    }
    @GetMapping("/test1")
//    @PreAuthorize("hasAnyAuthority ('student')")
    public List<User> test1(){
//         String sql = "select * from test_demo";
        List<User> temp = userMapper.querUserList();


        return  temp;
    }
    @PostMapping("/login")
    public ResultUtil Login(@RequestBody userLogin userLogin, HttpServletRequest request)
    {

        ResultUtil resultUtil=loginServers.login(userLogin,request);

         return resultUtil;
    }
    @PostMapping("/register")
    public  ResultUtil register(@RequestBody userRegister userRegister, HttpServletRequest request)
    {
        ResultUtil resultUtil= loginServers.insertUser(userRegister,request);
        return  resultUtil;
    }
    @PostMapping("/getUserInfo")
    public ResultUtil getUserInfo(HttpServletRequest request) throws Exception {

        ResultUtil resultUtil= loginServers.getUserInfo(request);

       return resultUtil;
    }
    @PostMapping("/updatePassW")
    public  ResultUtil updatePassWord(@RequestBody updatePW updatePW) throws Exception {
        ResultUtil resultUtil= loginServers.updatePassWord(updatePW);
        return resultUtil;
    }
    @PostMapping("/updateUserInfo")
    public  ResultUtil updateUserInfo(@RequestBody updateUserInfo userInfo) throws Exception {
        ResultUtil resultUtil= loginServers.updateUserInfo(userInfo);
        return resultUtil;
    }
    @GetMapping("/userDetailInfo")
    public  ResultUtil userDetailInfo (String userID)
    {
        return  loginServers.userDetailInfo(userID);
    }
    @PostMapping("/getOrders")
    public  ResultUtil getOrder(@RequestParam(name="userID",required = false)  String userID,@RequestParam(name="model",required = false)  String model) throws Exception {
        ResultUtil resultUtil= loginServers.getOrder( userID, model);
        return resultUtil;
    }
    @PostMapping("/getContent")
    public  ResultUtil getContent(@RequestParam(name="userID",required = false) String userID) throws Exception {
        ResultUtil resultUtil= loginServers.getContent( userID);
        return resultUtil;
    }
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //响应立即过期
        response.setDateHeader("Expires",0);
        //不缓存任何图片数据
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        //为了浏览器的兼容性
        response.setHeader("cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        //返回的内容类型  为图片格式
        response.setContentType("image/png");
        String text=defaultKaptcha.createText();

        request.getSession().setAttribute("verifyCode",text);
        BufferedImage image = defaultKaptcha.createImage(text);
       ServletOutputStream outputStream=response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();


    }
}
