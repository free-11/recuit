package org.example.recruit.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.exception.BusinessException;
import org.example.recruit.exception.DeleteFailedException;
import org.example.recruit.exception.LoginFailedException;
import org.example.recruit.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 用于统一捕获和处理所有异常，返回标准化的响应格式
 * 使用@RestControllerAdvice注解，作用于所有Controller
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /**
     * 处理业务异常
     * @param e 业务异常对象
     * @return 标准化错误响应
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        if (e.getCode() >= 500) {
            log.error("业务异常：{}", e.getMessage());
        } else {
            log.warn("业务异常：{}", e.getMessage());
        }
        return Result.error(e.getCode(), e.getMessage());
    }
    
    /**
     * 处理登录失败异常
     * @param e 登录失败异常对象
     * @return 标准化错误响应（错误码401）
     */
    @ExceptionHandler(LoginFailedException.class)
    public Result<?> handleLoginFailedException(LoginFailedException e) {
        log.error("登录失败：{}", e.getMessage());
        return Result.error(401, e.getMessage());
    }
    
    /**
     * 处理删除失败异常
     * @param e 删除失败异常对象
     * @return 标准化错误响应（错误码400）
     */
    @ExceptionHandler(DeleteFailedException.class)
    public Result<?> handleDeleteFailedException(DeleteFailedException e) {
        log.error("删除失败：{}", e.getMessage());
        return Result.error(400, e.getMessage());
    }
    
    /**
     * 处理系统异常
     * @param e 系统异常对象
     * @return 标准化错误响应（错误码500）
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage(), e);
        return Result.error(500, "系统内部错误：" + e.getMessage());
    }
}