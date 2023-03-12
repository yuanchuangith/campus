package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.server.mapper.userMapper;
import com.example.server.model.LoginUser;
import com.example.server.model.entity.RechargeTrade;
import com.example.server.model.view.accountEntity.userLogin;
import com.example.server.model.view.accountEntity.userRegister;
import com.example.server.model.entity.User;
import com.example.server.model.view.userEntity.getContentInfo;
import com.example.server.model.vo.user.updatePW;
import com.example.server.model.vo.user.updateUserInfo;
import com.example.server.model.vo.user.userContentInfo;
import com.example.server.service.userServe;
import com.example.server.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

//public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService{
@Service
public class UserServerImpl extends ServiceImpl<userMapper, User> implements userServe
{
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
        private userMapper userMapper;
    @Autowired
    private getLoginUser  getLoginUser;
    @Override
    public ResultUtil login(userLogin userLogin,HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("verifyCode");
        if (captcha == null || !captcha.equalsIgnoreCase(userLogin.getCode())) {
            return ResultUtil.fail(1001,"验证码错误");
        }

        LoginUser user =getLoginUser.getLoginUserByName(userLogin.getUsername(),0);

        if (Objects.isNull(user)){
            return ResultUtil.fail(1002,"暂无用户，请注册");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
                return ResultUtil.fail(1003,"用户名或者密码错误");
        }


        String userid=user.getUser().getUserID();
        String  jwt= JwtUtil.CreateJwt(userid);
        Claims claims = Jwts.claims();
        claims.put("userid",userid);
        Map<String,Object>data=new HashMap<>();
        data.put("token",jwt);
        data.put("username",user.getUser().getUserName());
        data.put("UserID",user.getUser().getUserID());
        data.put("expir",System.currentTimeMillis()+JwtUtil.jwt_ttl);
        redisCache.setJSONString("loginuser"+userid,user,redisCache.userLoginTime);


        UsernamePasswordAuthenticationToken usernamePasswordAuth=
                new UsernamePasswordAuthenticationToken(user.getUser(),null,null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);

        return ResultUtil.success(200,"成功",data);
    }

    @Override
    public ResultUtil insertUser(userRegister userregister, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("verifyCode");
        if (captcha == null || !captcha.equalsIgnoreCase(userregister.getCode())) {
            return ResultUtil.fail(1001,"验证码错误");
        }
        String username= userregister.getUserName();
        String password=userregister.getPassWord();
        String repeatPassword=userregister.getRepeatPassWord();
        int sexTag=userregister.getSex();

        if(username.isEmpty() || password.isEmpty()|| repeatPassword.isEmpty())
            return ResultUtil.fail(1005,"请输入您的完整信息");
//        boolean temp=password.equals(repeatPassword);
        if (!password.equals(repeatPassword))
            return ResultUtil.fail(1006,"请输入您的完整信息");
        String IP = getIp.getIpAddress(request);
        User userInfo =new User();
        userInfo.setUserName(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        userInfo.setPassWord(bCryptPasswordEncoder.encode(password));
        userInfo.setSex(sexTag);
        userInfo.setBirthday(userregister.getBirthday());
        if(userregister.getProtraitPhoto()==null||userregister.getProtraitPhoto()==null)
            userInfo.setProtraitPhoto("null");
        else
            userInfo.setProtraitPhoto(userregister.getProtraitPhoto());

        userInfo.setTelephone(userregister.getTelephone());
        userInfo.setCreateBy(userregister.getCreateBy());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setLoginIp(IP);
        userInfo.setLocked(0);
        userInfo.setUserID(UUID.randomUUID().toString().replace("-",""));
        userInfo.setDelTag(0);
        userInfo.setOnceUpdateSex(1);

       int result= userMapper.insert(userInfo);
       if (result>0) {
           String nameInfo = userregister.getUserName()+"|0";
           LoginUser user =getLoginUser.getLoginUserByName(userregister.getUserName(),0);
           String userid=user.getUser().getUserID();
           String  jwt= JwtUtil.CreateJwt(userid);
           Claims claims = Jwts.claims();
           claims.put("userid",userid);
           Map<String,Object>data=new HashMap<>();
           data.put("token",jwt);
           data.put("username",user.getUser().getUserName());
           data.put("UserID",user.getUser().getUserID());
           data.put("expir",System.currentTimeMillis()+JwtUtil.jwt_ttl);

           redisCache.setJSONString("loginuser"+userid,user,redisCache.userLoginTime);


           UsernamePasswordAuthenticationToken usernamePasswordAuth=
                   new UsernamePasswordAuthenticationToken(user.getUser(),null,null);
           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
           return ResultUtil.success(200,"注册成功",data);
       }
       else
        return ResultUtil.fail(404,"注册失败");
    }

    @Override
    public ResultUtil getUserInfo(HttpServletRequest request) throws Exception {
        String token= request.getHeader("token");

        if (token==null)  {
            return  ResultUtil.fail(40002,"请登录");
        }
        String expir =request.getHeader("expir");
        boolean tokenExpir=JwtUtil.isTokenExpir(expir);
         if (!tokenExpir) {
            return  ResultUtil.fail(40003,"登录信息已失效,请重新登录");
        }
        Claims claims = JwtUtil.parsetJwt(token);

        if(Objects.isNull(claims))
        {
            return  ResultUtil.fail(40003,"登录信息已失效,请重新登录");
        }

        String userid= claims.getSubject();
        LoginUser loginUser=redisCache.getObjectClass("loginuser"+userid,LoginUser.class);

        if(Objects.isNull(loginUser))
        {
            return  ResultUtil.fail(40003,"登录信息已失效,请重新登录");
        }
        return  ResultUtil.success(200,"成功",loginUser.getUser());
    }

    @Override
    public ResultUtil updatePassWord(updatePW updatePW) throws Exception {
        if(Objects.isNull(updatePW)||updatePW.getUserID()==null||updatePW.getNewPassWord()==null||updatePW.getOldPassWord()==null)
            return ResultUtil.fail(1006,"请输入您的完整信息");

        LoginUser user =getLoginUser.getLoginUserById(updatePW.getUserID(),0);

        if (Objects.isNull(user)){
            return ResultUtil.fail(1002,"暂无用户，请注册");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(updatePW.getOldPassWord(), user.getPassword())) {
            return ResultUtil.fail(1003,"密码错误");
        }
        String password=bCryptPasswordEncoder.encode(updatePW.getNewPassWord());
        userMapper.updatePW( password,updatePW.getUserID());
        user.getUser().setPassWord(password);
        redisCache.setJSONString("loginuser"+user.getUser().getUserID(),user,redisCache.userLoginTime);
        return  ResultUtil.success(200,"修改成功",null);

    }

    @Override
    public ResultUtil updateUserInfo(updateUserInfo userInfo) throws Exception {
        LocalDateTime now= LocalDateTime.now();
        if(Objects.isNull(userInfo)&&userInfo.getBirthday()==null&&userInfo.getSex()==null&&userInfo.getRealName()==null||userInfo.getUserID()==null)
            return ResultUtil.fail(1004,"请输入你需要修改的信息");
        LoginUser loginUser=redisCache.getObjectClass("loginuser"+userInfo.getUserID(),LoginUser.class);
        if(Objects.isNull((loginUser)))
            return ResultUtil.fail(10003,"请登录");
//        if(userInfo.getBirthday()!=null)
//        {
//           String dateStr = userInfo.getBirthday().replace("T", " UTC");//是空格+UTC
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//            userInfo.setBirthday(String.valueOf(df.parse(dateStr)));
//
//
//
//        }
        if(userInfo.getSex()!=null&&loginUser.getUser().getOnceUpdateSex()==0)
        {
            userMapper.updateUserInfo(userInfo.getBirthday(),userInfo.getRealName(),null,userInfo.getUserID(),LocalDateTime.now());
            LoginUser newuser =getLoginUser.getLoginUserById(loginUser.getUser().getUserID(),0);
            redisCache.setJSONString("loginuser"+newuser.getUser().getUserID(),newuser,redisCache.userLoginTime);
            return ResultUtil.success(200,"性别只能修改一次，其他信息修改成功",null);
        }
        userMapper.updateUserInfo(userInfo.getBirthday(),userInfo.getRealName(),String.valueOf(userInfo.getSex()),userInfo.getUserID(),LocalDateTime.now());
        LoginUser newuser =getLoginUser.getLoginUserById(loginUser.getUser().getUserID(),0);
        redisCache.setJSONString("loginuser"+newuser.getUser().getUserID(),newuser,redisCache.userLoginTime);
        return ResultUtil.success(200,"修改成功",null);

    }

    @Override
    public ResultUtil userDetailInfo(String userID) {
        if(userID==null)
            return ResultUtil.fail(10003,"请登录");
        LoginUser loginUser=redisCache.getObjectClass("loginuser"+userID,LoginUser.class);
        if(Objects.isNull((loginUser)))
            return ResultUtil.fail(10003,"请登录");


        userContentInfo userContentInfo=userMapper.getUserContentInfo(userID);

        HashMap <String,Object  > data= new HashMap<>();
        data.put("user",loginUser.getUser());
        data.put("userContentInfo",userContentInfo);
        return ResultUtil.success(200,"成功",data);
    }

    @Override
    public ResultUtil getOrder(String userID, String model) {
            if(userID==null||model==null)
                return ResultUtil.fail(10003,"请登录");
            if(model.equals("1")||model=="1")
            {
                List<RechargeTrade> order = userMapper.getOrder(userID, null);

                return  ResultUtil.success(200,"成功",order);
            }
            else if(model.equals("2")||model=="2")
            {
                List<RechargeTrade> order = userMapper.getOrder(userID, "1");

                return  ResultUtil.success(200,"成功",order);
            }
            else {
                List<RechargeTrade> order = userMapper.getOrder(userID, "0");

                return  ResultUtil.success(200,"成功",order);
            }


    }

    @Override
    public ResultUtil getContent(String userID) {
        if(userID==null)
            return ResultUtil.fail(10003,"请登录");
        List<getContentInfo> userContent = userMapper.getUserContent(userID);

         return  ResultUtil.success(200,"成功",userContent);
    }

//    public  LoginUser getLoginUser(String userName)
//    {
//        Userinfo user =userMapper.getUser(userName);
//        if (Objects.isNull(user)){
//            return null;
//        }
//        else {
//            List <String> auth = new ArrayList<>(Arrays.asList("admin","test"));
//            return new LoginUser(user,auth);
//        }
//    }
}
