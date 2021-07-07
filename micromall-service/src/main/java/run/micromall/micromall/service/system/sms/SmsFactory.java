package run.micromall.micromall.service.system.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信服务工厂
 *
 * @author Administrator
 * @since 2021/7/7
 */
@Service
public class SmsFactory {

    @Autowired
    private Map<String, SmsSender> smsSenderMap = new ConcurrentHashMap<>();

    public SmsSender getSmsSender(String component) {
        SmsSender smsSender = smsSenderMap.get(component);
        if (smsSender == null) {
            throw new NullPointerException("no strategy defined");
        }
        return smsSender;
    }
}
