package com.example.server.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.jsonwebtoken.*;



import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JwtUtil {
//    40*60*24L一分钟的时间
    public  static  final  Long jwt_ttl =40*60*24L*60*2;
    public static final  String Jwt_key="yc";
    public static final  String Jwt_keyCheckCode="captcha28";
    public static  String getUUid(){
        return UUID.randomUUID().toString().replace("-","");

    }
//    jwt生成
    public static  String CreateJwt(String subJect){
        JwtBuilder builder=getJwtBuilder(subJect,null,getUUid());
//        System.out.println(new Date());
        return builder.compact();
    }
    //    jwt生成
    public static  String CreateJwtNoTime(String subJect){
        JwtBuilder builder=getJwtBuilder(subJect,getUUid());
//        System.out.println(new Date());
        return builder.compact();
    }

    public static  String CreateJwt(String subJEect,long time)
    {
        Long ttImillis=time*JwtUtil.jwt_ttl;
        JwtBuilder builder =getJwtBuilder(subJEect,ttImillis,getUUid());
        return builder.compact();
    }
    public static  String CreateJwt(String subJEect,long time,int userKeyModel)
    {
        Long ttImillis=time*JwtUtil.jwt_ttl;
        JwtBuilder builder =getJwtBuilder(subJEect,ttImillis,getUUid(),userKeyModel);
        return builder.compact();
    }

    public static boolean isTokenExpired(String token) throws Exception {
        Date temp=getUserToekn(token);
       return  temp== null ? true:false;
    }
    public static boolean isTokenExpir(String expir)
    {
   try{
       Long expirLong= Long.parseLong(expir);
       long nowMil= System.currentTimeMillis();
       if(expirLong>nowMil){
           return true;
       }
       else
           return false;

   }catch (Exception e){
       return  false;
   }

    }
//    private static  Date getExpiredDatFromToke(String token)
//    {
//        Claims claims =
//    }
    public static Date  getUserToekn(String token) throws Exception {

        try{
            Claims claims= parsetJwt(token);
            Date expiration = claims.getExpiration();
            return expiration;

        }
        catch (Exception e){
            return  null;
        }


    }
//0表示使用Jwt_key，Jwt_keyCheckCode
    private static  JwtBuilder getJwtBuilder(String subJect,Long ttImillis,String uuid){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        SecretKey secretKey= generalKey();
        long nowMill=System.currentTimeMillis();
        Date now =new Date(nowMill);
        if(ttImillis==null)
        {
            ttImillis=JwtUtil.jwt_ttl;
        }
        long expMillis=nowMill+ttImillis;
        Date expDate=new Date(expMillis);

        return Jwts.builder().setId(uuid)//设置唯一的id
                .setSubject(subJect) //内容
                .setIssuer(Jwt_key)  //发布者
                .setIssuedAt(now) //加密时间
                .signWith(signatureAlgorithm,secretKey)
                .setExpiration(expDate);


    }
    private static  JwtBuilder getJwtBuilder(String subJect,Long ttImillis,String uuid,int userKeyModel){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        SecretKey secretKey= generalKey();
        long nowMill=System.currentTimeMillis();
        Date now =new Date(nowMill);
        if(ttImillis==null)
        {
            ttImillis=JwtUtil.jwt_ttl;
        }
        long expMillis=nowMill+ttImillis;
        Date expDate=new Date(expMillis);
        if (userKeyModel==1)
        return Jwts.builder().setId(uuid)//设置唯一的id
                .setSubject(subJect) //内容
                .setIssuer(Jwt_keyCheckCode)  //发布者
                .setIssuedAt(now) //加密时间
                .signWith(signatureAlgorithm,secretKey)
                .setExpiration(expDate);
        else
            return Jwts.builder().setId(uuid)//设置唯一的id
                    .setSubject(subJect) //内容
                    .setIssuer(Jwt_key)  //发布者
                    .setIssuedAt(now) //加密时间
                    .signWith(signatureAlgorithm,secretKey)
                    .setExpiration(expDate);



    }
    private static  JwtBuilder getJwtBuilder(String subJect,String uuid){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        SecretKey secretKey= generalKey();
        long nowMill=System.currentTimeMillis();
        Date now =new Date(nowMill);




            return Jwts.builder().setId(uuid)//设置唯一的id
                    .setSubject(subJect) //内容
                    .setIssuer(Jwt_keyCheckCode)  //发布者
                    .setIssuedAt(now) //加密时间
                    .signWith(signatureAlgorithm,secretKey);




    }

    public static  String CreatJwt(String ID,String subject,Long ttlimisis){
        JwtBuilder builder =getJwtBuilder(subject,ttlimisis,ID);
        return builder.compact();
    }

    public  static  SecretKey generalKey(){
        byte[] enCodekey= Base64.getDecoder().decode(Jwt_key);
        SecretKey key = new SecretKeySpec(enCodekey,0,enCodekey.length,"AES");
        return  key;
    }
public  static  void  main (String[] args)throws  Exception{
        String toke=CreateJwt("zhangsan",2);
////    String  toke="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1ZjcxZjQwN2RmZWM0ODc1YWRiZDc0YTg5MzE1Y2RiYSIsInN1YiI6InpoYW5nc2FuIiwiaXNzIjoieWMiLCJpYXQiOjE2NjkyMDQ1MTEsImV4cCI6MTY2OTIwNDU0N30.eqw06w-KqspGlKld90Mez4vpnQecD_f3pS9jSLdXoBU\n" +
//                "zhangsan";
//    Claims claims= parsetJwt(toke);
//   boolean a= isTokenExpired(toke);
//    System.out.println(a);
//    String subJect = claims.getSubject();
//    System.out.println(subJect);
//    Claims claim= parsetJwt(toke);
//    claim.getSubject();
//
//    System.out.println(toke);
//
////    System.out.println(subJect);
////    TimeUnit.SECONDS.sleep(60);//秒
//    System.out.println(isTokenExpired(toke));
    DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
    String path="E:/User/Desktop/campus/school/tempImg";
    File folder = new File(path);
    if (!folder.exists()) {
        folder.mkdirs();
    }

    String text=defaultKaptcha.createText();
    String name=text+".jpg";
    BufferedImage image = defaultKaptcha.createImage(text);



}
public static  Claims parsetJwt(String jwt) throws  Exception{
        SecretKey key = generalKey();
    Claims claims;
    try{
        claims= Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;

    }
    catch (ExpiredJwtException e)
    {
        return null;
    }

//        return  Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
}

}
