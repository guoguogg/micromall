package run.micromall.micromall.service.system.sms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.system.properties.SmsProperties;
import run.micromall.micromall.service.system.service.MicroMallConfigService;
import run.micromall.micromall.service.utils.JacksonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 短信服务类
 *
 * @author songhaozhi
 * @since 2021/7/7
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsService {

    private final MicroMallConfigService configService;

    private final SmsFactory smsFactory;

    /**
     * 短信模版消息通知
     *
     * @param mobile     接收通知的电话号码
     * @param notifyType 通知类别，通过该枚举值在配置文件中获取相应的模版ID
     * @param params     通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
     */
    public SmsResult smsSender(String mobile, NotifyType notifyType, String[] params) {
        Optional<String> activeOptional = configService.getByProperties(SmsProperties.MICROMALL_ACTIVE, String.class);
        if (activeOptional.isPresent()) {
            String active = activeOptional.get();
            if ("false".equals(active)) {
                return null;
            }
            Optional<String> smsTemplate = configService.getByProperties(SmsProperties.MICROMALL_SMS_TEMPLATE, String.class);
            List<Map<String, String>> smsTemplateMap = new ArrayList<>();
            if (smsTemplate.isPresent()) {
                smsTemplateMap = JacksonUtil.toListMap(smsTemplate.get());
            }
            return smsFactory.getSmsSender(active).sendCode(mobile, getTemplateId(notifyType, smsTemplateMap), params);
        }
        return null;
    }

    private String getTemplateId(NotifyType notifyType, List<Map<String, String>> values) {
        for (Map<String, String> item : values) {
            String notifyTypeStr = notifyType.getType();
            if (item.get("name").equals(notifyTypeStr)) {
                return item.get("templateId");
            }
        }
        return null;
    }

}
