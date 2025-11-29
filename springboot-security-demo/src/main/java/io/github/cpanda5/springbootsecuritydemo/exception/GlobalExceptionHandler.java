package io.github.cpanda5.springbootsecuritydemo.exception;

import io.github.cpanda5.springbootsecuritydemo.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常（可以返回给前端）
     */
    @ExceptionHandler(LeaseException.class)
    @ResponseBody
    public Result<?> handleLeaseException(LeaseException e){
        log.error("[BizException] code={}, msg={}", e.getCode(), e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 系统异常（不返回真实错误）
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> handleException(Exception e){
        log.error("[SystemException] {}", e.getMessage(), e);
        return Result.fail(500, "系统异常，请稍后再试");
    }
}