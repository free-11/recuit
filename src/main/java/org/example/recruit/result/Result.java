package org.example.recruit.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果类
 * 用于规范API响应格式，包含状态码、消息和数据
 * @param <T> 响应数据类型
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 状态码
     * 200: 成功
     * 400: 业务错误
     * 500: 系统错误
     */
    private Integer code;
    
    /**
     * 响应消息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private T data;
    
    /**
     * 成功响应（无数据）
     * @param <T> 响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }
    
    /**
     * 成功响应（带数据）
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }
    
    /**
     * 错误响应（指定错误码和消息）
     * @param code 错误码
     * @param message 错误消息
     * @param <T> 响应数据类型
     * @return 错误响应对象
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    
    /**
     * 错误响应（默认错误码400）
     * @param message 错误消息
     * @param <T> 响应数据类型
     * @return 错误响应对象
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(400);
        result.setMessage(message);
        return result;
    }
}
