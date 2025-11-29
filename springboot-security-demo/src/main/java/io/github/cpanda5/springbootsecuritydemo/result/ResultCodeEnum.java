package io.github.cpanda5.springbootsecuritydemo.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    PARAM_ERROR(401, "参数不正确"),
    SERVICE_ERROR(402, "服务异常"),
    DATA_ERROR(403, "数据异常"),
    ILLEGAL_REQUEST(404, "非法请求"),
    REPEAT_SUBMIT(405, "重复提交"),

    ADMIN_ACCOUNT_EXIST(301, "账号已存在"),
    ADMIN_ACCOUNT_NOT_EXIST(302, "账号不存在"),
    ADMIN_ACCOUNT_ERROR(303, "用户名或密码错误"),
    ADMIN_ACCOUNT_DISABLED(304, "该用户已被禁用"),
    ADMIN_LOGIN_AUTH(305, "未登录"),
    ADMIN_ACCESS_FORBIDDEN(306, "无访问权限"),

    APP_LOGIN_AUTH(501, "未登录"),
    APP_PHONE_EXIST(502, "手机号已存在"),
    APP_ACCOUNT_DISABLED(503, "该用户已被禁用"),
    APP_PASSWORD_NOT_MATCH(504, "两次输入的密码不一致"),
    APP_LOGIN_CODE_ERROR(505, "验证码错误"),
    APP_LOGIN_CODE_EXPIRED(506, "验证码已过期"),

    TOKEN_EXPIRED(601, "Token已过期"),
    TOKEN_INVALID(602, "Token非法");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
