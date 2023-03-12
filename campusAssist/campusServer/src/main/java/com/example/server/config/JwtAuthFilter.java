package com.example.server.config;


import com.alibaba.fastjson.JSON;
import com.example.server.model.LoginUser;
import com.example.server.util.JwtUtil;
import com.example.server.util.RedisCache;
import com.example.server.util.ResultUtil;
import com.example.server.util.getLoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    private static final ArrayList urls   =new ArrayList<>(Arrays.asList("/Account/info"


    ));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String token= request.getHeader("token");
        String requestURI = request.getRequestURI();
      if (token==null)  {
          if (!urls.contains(requestURI))
          {
              filterChain.doFilter(request,response);
              return;
          }
          else {
              response.setCharacterEncoding("UTF-8");
              response.setContentType("text/html; charset=utf-8");
              PrintWriter writer = response.getWriter();
              writer.print(JSON.toJSONString( ResultUtil.fail(4000, "请登录")));
          }
        }
      else {


          if (!urls.contains(requestURI))
          {
              filterChain.doFilter(request,response);
              return;
          }

          try{
              String expir =request.getHeader("expir");
              boolean tokenExpir=JwtUtil.isTokenExpir(expir);

//              if (expir==null)
//              {
//                  Boolean expiris=JwtUtil.isTokenExpired(token);
//                  throw new RuntimeException("用户位登录或者登录过期");
//              }

              if (!tokenExpir) {
                  response.setCharacterEncoding("UTF-8");
                  response.setContentType("text/html; charset=utf-8");
                  PrintWriter writer = response.getWriter();
                  writer.print(JSON.toJSONString( ResultUtil.fail(40003, "登录信息已失效,请重新登录")));


                  System.out.println("用户位登录或者登录过期");
                  return;
              }

              Claims claims = JwtUtil.parsetJwt(token);

              if(Objects.isNull(claims))
              {
                  filterChain.doFilter(request,response);
                  return;
              }

              String userid= claims.getSubject();
              LoginUser loginUser=redisCache.getObjectClass("loginuser"+userid,LoginUser.class);

              if(Objects.isNull(loginUser))
                {
                    throw new RuntimeException("用户位登录或者登录过期");
                }
              if(! Objects.isNull(loginUser)  )
              {
                  UsernamePasswordAuthenticationToken usernamePasswordAuth=
                          new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

                  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);



              }



//            Object temp=  SecurityContextHolder.getContext().getAuthentication();


              filterChain.doFilter(request,response);
              return;
          }
          catch (Exception e){
              e.printStackTrace();

          }



      }


    }
}
