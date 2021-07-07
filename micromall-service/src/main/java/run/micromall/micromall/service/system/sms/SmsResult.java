package run.micromall.micromall.service.system.sms;

import lombok.Data;

/**
 * 发送消息结果
 *
 * @author Administrator
 * @since 2021/7/7
 */
@Data
public class SmsResult {
    private boolean successful;
    private Object result;
}
