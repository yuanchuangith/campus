package com.example.server.util;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUtil {
    private int code;
    private String Message;
    private Object data;

    public static ResultUtil success(int code, String msg, Object data) {
        ResultUtil ResultUtil = new ResultUtil();
        ResultUtil.setCode(code);
        ResultUtil.setMessage(msg);
        ResultUtil.setData(data);
        return ResultUtil;
    }

    public static ResultUtil success() {
        return success(200, null, null);
    }

    public static ResultUtil success(String msg) {
        return success(200, msg, null);
    }

    public static ResultUtil success(String msg, Object data) {
        return success(200, msg, data);
    }

    public static ResultUtil fail(int code, String msg, Object data) {
        ResultUtil ResultUtil = new ResultUtil();
        ResultUtil.setCode(code);
        ResultUtil.setMessage(msg);
        ResultUtil.setData(data);
        return ResultUtil;
    }

    public static ResultUtil fail(int code) {
        return success(code, null, null);
    }

    public static ResultUtil fail(int code, String msg) {
        return success(code, msg, null);
    }

    public static ResultUtil fail() {
        return success(404, null, null);
    }
}

