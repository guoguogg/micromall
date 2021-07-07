package run.micromall.micromall.service.system.sms;

import org.springframework.stereotype.Component;

/**
 * 阿里云短信服务
 *
 * @author Administrator
 * @since 2021/7/7
 */
@Component("AliSms")
public class AliyunSmsSender implements SmsSender {
    @Override
    public SmsResult sendCode(String mobile, String content) {
        return null;
    }
}
