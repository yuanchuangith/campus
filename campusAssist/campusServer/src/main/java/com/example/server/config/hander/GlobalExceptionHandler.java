package com.example.server.config.hander;

import com.example.server.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultUtil handleException(Exception exception) {
        String message = exception.getLocalizedMessage();
        System.out.println(message);
        System.out.println("Exception");
//        log.error("全局异常捕获Exception：{}", message, exception);

        return ResultUtil.fail(4000,"系统错误");
    }
@ExceptionHandler(AuthenticationException.class)
public ResultUtil handleAuthenticationException(Exception exception) {
    String message = exception.getLocalizedMessage();
    System.out.println("handleAuthenticationException");
    System.out.println(message);
//        log.error("全局异常捕获Exception：{}", message, exception);

    return ResultUtil.fail(4001,"未登录或者登录过期");
}
//    @ExceptionHandler(CommonException.class)
//    public ResponseEntity<RestResponse<Object>> handleException(CommonException exception) {
//        String message = exception.getLocalizedMessage();
//        log.error("全局异常捕获CommonException：{}", message);
//        return RestResponse.buildError(exception.getBusinessStatus(), message);
//    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResultUtil handleException(AccessDeniedException exception) {
        String message = exception.getLocalizedMessage();
        System.out.println(message);

        return ResultUtil.fail(4002,"未获得响应访问权限");
    }


}
