package io.github.cpanda5.springbootsecuritydemo.result;

import lombok.Data;
import java.util.LinkedHashMap;

/**
 * 全局统一返回结果类
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    private Result() {}

    /** 内部创建方法 */
    private static <T> Result<T> create(Integer code, String message, T data) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    /* ====================== 成功 ====================== */

    /** 成功（带数据） */
    public static <T> Result<T> ok(T data) {
        return create(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(),
                data);
    }

    /** 成功（无数据） */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    /* ====================== 失败 ====================== */

    /** 失败（默认 FAIL） */
    public static <T> Result<T> fail() {
        return fail(ResultCodeEnum.FAIL);
    }

    /** 失败（使用枚举） */
    public static <T> Result<T> fail(ResultCodeEnum codeEnum) {
        return create(codeEnum.getCode(),
                codeEnum.getMessage(),
                emptyObject());
    }

    /** 失败（自定义 code + message） */
    public static <T> Result<T> fail(Integer code, String message) {
        return create(code, message, emptyObject());
    }

    /** 返回 {} 作为 data */
    @SuppressWarnings("unchecked")
    private static <T> T emptyObject() {
        return (T) new LinkedHashMap<String, Object>();
    }
}
