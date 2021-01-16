package run.micromall.micromall.service.response;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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

    private String code;

    private T data;

    private String msg;

    private LocalDateTime time;

    public ResponseUtil() {

    }

    public static ResponseUtil result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail("");
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
        String message = responseCode.getMsg();
        if (StringUtils.isNotBlank(msg)) {
            message = msg;
        }
        return ResponseUtil.builder()
                .code(responseCode.getCode())
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

    public static ResponseUtil fail(String responseCode, String apiValue, Object value) {
        return result(responseCode, apiValue, value);
    }

    public static ResponseUtil fail(ResponseCode responseCode, Object data) {
        if (ResponseCode.SUCCESS == responseCode) {
            throw new RuntimeException("失败结果状态码不能为" + ResponseCode.SUCCESS.getCode());
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