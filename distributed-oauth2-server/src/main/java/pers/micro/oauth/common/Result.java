package pers.micro.oauth.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回响应信息主体
 *
 * @author weixun
 */
@Data
public class Result<T> implements Serializable {

    //返回状态码
    private Integer code;

    //返回消息
    private String msg;

    //返回数据
    private T data;

    private Result() {
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }

    public static Result error(ResultCode resultCode) {
        return error(resultCode.getCode(), resultCode.getMsg());
    }
}
