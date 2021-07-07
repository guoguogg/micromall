package run.micromall.micromall.service.system.sms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import run.micromall.micromall.service.system.service.MicroMallConfigService;

import java.util.Arrays;
/**
 * 腾讯云短信服务
 *
 * @author Administrator
 * @since 2021/7/7
 */
@Component("TencentSms")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TencentSmsSender implements SmsSender {

    private final MicroMallConfigService configService;

    @Override
    public SmsResult sendCode(String mobile, String templateId, String[] params) {

        log.debug("参数：{},{},{}", mobile, templateId, Arrays.toString(params));
        log.debug("腾讯云短信");
        return null;
    }

}
