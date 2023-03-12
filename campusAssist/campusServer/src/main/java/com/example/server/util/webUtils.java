package com.example.server.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class webUtils {
    //讲字符串渲染到客户端
    public static  String  resendString(HttpServletResponse response ,String str)
    {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
