package org.amrwcs.gdpuseizingcourses.handler;

import lombok.extern.slf4j.Slf4j;
import org.amrwcs.gdpuseizingcourses.common.exception.CookieExpiredException;
import org.amrwcs.gdpuseizingcourses.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理Cookie过期异常
     * @param e
     * @return
     */
    @ExceptionHandler(CookieExpiredException.class)
    public Result<?> handleCookieExpired(CookieExpiredException e) {
        blockLogOutput(e);
        return Result.error(e.getMessage());
    }

    /**
     * 处理其它RuntimeException异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleRuntime(RuntimeException e) {
        blockLogOutput(e);
        return Result.error(e.getMessage());
    }

    //输出拦截日志
    private void blockLogOutput(Exception e){
        log.error("拦截操作：\"{}\"",e.getMessage());
    }
}
