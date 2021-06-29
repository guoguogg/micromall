package run.micromall.micromall.service.response;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API 返回结果
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ResponseUtil<T> implements Serializable {

    public enum ResponseCode {

        SUCCESS("200", "操作成功"),
        ERROR_INVALID_MOBILE("1", "无效的电话号码"),
        ERROR_SEND_SMS_OVER_FREQUENCY("3", "请求验证码太频繁"),
        ERROR_SERVER_ERROR("4", "服务器异常"),
        ERROR_CODE_EXPIRED("5", "验证码已过期"),
        ERROR_CODE_INCORRECT("6", "验证码错误"),
        ACCOUNT_DISABLED("7", "该账号已被禁用"),
        CONTAIN_SENSITIVE_WORD("8", "包含敏感词"),
        BAD_REQUEST("400", "参数错误"),
        UNAUTHORIZED("401", "请登录"),
        NOT_PERMISSION("403", "没有权限"),
        NOT_FOUND("404", "你请求的资源不存在"),
        REFRESH_TOKEN_OVERDUE("405", "token过期"),
        TOO_MANY_REQUESTS( "429", "请求太频繁,请稍后再试" ),
        FAIL("500", "操作失败"),

        OPTIMISTIC_ERROR("501", "请求数据已失效,请稍后重试"),

        LOGIN_EXCEPTION("4000", "登陆失败"),

        SYSTEM_EXCEPTION("5000", "系统异常,请联系管理员"),

        PARAMETER_EXCEPTION("5001", "请求参数校验异常"),

        PARAMETER_PARSE_EXCEPTION("5002", "请求参数解析异常"),

        HTTP_MEDIA_TYPE_EXCEPTION("5003", "HTTP Media 类型异常"),

        DATEBASE_EXECUTION_EXCEPTION("5004", "数据库执行语句出现异常"),
        REQUESTMETHODNOTSUPPORTED("5005", "不支持的请求方式"),

        ;
        public String code;
        public String msg;
        ResponseCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
    private String code;

    private T data;

    private String msg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public ResponseUtil() {

    }

    public static ResponseUtil result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail("");
    }

    /**
     * 请求太频繁
     */
    public static <T> ResponseUtil<T> requestTooFast(T data) {
        return fail(ResponseCode.TOO_MANY_REQUESTS.code, ResponseCode.TOO_MANY_REQUESTS.msg, data);
    }

    public static ResponseUtil result(ResponseCode responseCode) {
        return result(responseCode, null);
    }

    public static ResponseUtil result(ResponseCode responseCode, Object data) {
        return result(responseCode, null, data);
    }

    public static ResponseUtil result(String responseCode, String msg) {
        return result(responseCode, msg, null);
    }

    public static ResponseUtil result(ResponseCode responseCode, String msg, Object data) {
        String message = responseCode.msg;
        if (StringUtils.isNotBlank(msg)) {
            message = msg;
        }
        return ResponseUtil.builder()
                .code(responseCode.code)
                .msg(message)
                .data(data)
                .time(LocalDateTime.now())
                .build();
    }

    public static ResponseUtil result(String responseCode, String apiValue, Object data) {
        return ResponseUtil.builder()
                .code(responseCode)
                .msg(apiValue)
                .data(data)
                .time(LocalDateTime.now())
                .build();
    }

    public static ResponseUtil ok() {
        return ok(null);
    }

    public static ResponseUtil ok(Object data) {
        return result(ResponseCode.SUCCESS, data);
    }

    public static ResponseUtil ok(Object data, String msg) {
        return result(ResponseCode.SUCCESS, msg, data);
    }

    public static ResponseUtil okMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return ok(map);
    }

    public static ResponseUtil fail(ResponseCode responseCode) {
        return result(responseCode, null);
    }

    public static ResponseUtil fail(String msg) {
        return result(ResponseCode.FAIL, msg, null);
    }
    public static ResponseUtil notAuthorized() {
        return result(ResponseCode.NOT_PERMISSION);
    }
    public static ResponseUtil notFound() {
        return result(ResponseCode.NOT_FOUND);
    }
    public static ResponseUtil notLogin() {
        return result(ResponseCode.UNAUTHORIZED);
    }

    public static ResponseUtil paramError(String msg) {
        return fail(ResponseCode.BAD_REQUEST,msg);
    }
    public static ResponseUtil paramError() {
        return fail(ResponseCode.BAD_REQUEST,"参数错误");
    }

    public static ResponseUtil fail(String responseCode, String apiValue, Object value) {
        return result(responseCode, apiValue, value);
    }

    public static ResponseUtil fail(ResponseCode responseCode, Object data) {
        if (ResponseCode.SUCCESS == responseCode) {
            throw new RuntimeException("失败结果状态码不能为" + ResponseCode.SUCCESS.code);
        }
        return result(responseCode, data);
    }

    public static ResponseUtil fail(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return result(ResponseCode.FAIL, map);
    }

    public static ResponseUtil fail() {
        return fail(ResponseCode.FAIL);
    }
}