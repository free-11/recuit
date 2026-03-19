package org.example.recruit.exception;

import org.example.recruit.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 用于统一捕获和处理所有异常，返回标准化的响应格式
 * 使用@RestControllerAdvice注解，作用于所有Controller
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 处理业务异常
     * @param e 业务异常对象
     * @return 标准化错误响应
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
    
    /**
     * 处理系统异常
     * @param e 系统异常对象
     * @return 标准化错误响应（错误码500）
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        return Result.error(500, "系统内部错误：" + e.getMessage());
    }
}