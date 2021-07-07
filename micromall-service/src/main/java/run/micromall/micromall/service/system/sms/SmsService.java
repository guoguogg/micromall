package run.micromall.micromall.service.system.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.service.system.service.MicroMallConfigService;

/**
 * 短信服务类
 *
 * @author Administrator
 * @since 2021/7/7
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsService {

    private final MicroMallConfigService configService;

    private final SmsFactory smsFactory;

    public SmsResult upload(String mobile, String content) {


        String type = "";
        SmsResult result = smsFactory.getSmsSender(type).sendCode(mobile,content);
        return result;
    }


}
