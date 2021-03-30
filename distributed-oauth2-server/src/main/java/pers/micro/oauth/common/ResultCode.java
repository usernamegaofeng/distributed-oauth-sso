package pers.micro.oauth.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Gaofeng
 * @date 2021/3/22 上午9:37
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    /**********通用返回码*************/

    SUCCESS(10000, "请求成功"),
    FAIL(10001,"请求失败"),
    INTERFACE_NOT_EXIST(11000, "接口不存在"),

    /**********认证服务返回码*************/

    CLIENT_AUTHENTICATION_FAILED(15000, "客户端认证失败"),
    TOKEN_INVALID_OR_EXPIRED(15001, "token无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN(15002, "token已被禁止访问"),
    AUTHORIZED_ERROR(15003, "token错误"),
    ACCESS_UNAUTHORIZED(15004, "该方法无权限访问"),
    REFRESH_TOKEN_EXPIRE(15005,"刷新token失效,请重新登录"),
    ;


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private Integer code;

    private String msg;
}