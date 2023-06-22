package com.zed.common;

/**
 * 接口统一返回包装类
 */
public class Result {
    private String code;
    private String message;
    private Object data;

    public Result(String code, String s, Object o) {

    }

    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    public static Result success(Object data) {
        return new Result(Constants.CODE_200, "", data);
    }

    public static Result error(String code, String message) {
        return new Result(code, message, null);
    }

    public static Result error() {
        return new Result(Constants.CODE_500, "系统错误", null);
    }
}
