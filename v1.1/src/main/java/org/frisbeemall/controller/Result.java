package org.frisbeemall.controller;

public class Result<T> {
    private int code; // 状态码
    private String message; // 消息
    private T data; // 数据

    // 构造函数
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getter 和 Setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 静态工厂方法
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    public static Result<String> error(String message) {
        return new Result<>(500, message, null);
    }
}
