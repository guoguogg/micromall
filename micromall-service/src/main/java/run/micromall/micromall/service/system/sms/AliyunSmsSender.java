package run.micromall.micromall.service.system.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import run.micromall.micromall.db.system.properties.SmsProperties;
import run.micromall.micromall.service.system.service.MicroMallConfigService;
import run.micromall.micromall.service.utils.JacksonUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云短信服务
 *
 * @author Administrator
 * @since 2021/7/7
 */
@Component("AliSms")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AliyunSmsSender implements SmsSender {

    private final MicroMallConfigService configService;

    private static class AliyunCommonResponse {
        String Message;
        String Code;
    }
    @Override
    public SmsResult sendCode(String mobile, String templateId, String[] params) {

        log.debug("参数：{},{},{}",mobile,templateId, Arrays.toString(params));

        String accessKeyId = configService.getByPropertyOrDefault(SmsProperties.MICROMALL_ALI_SMS_ACCESS_KEY_ID,
                String.class, SmsProperties.MICROMALL_ALI_SMS_ACCESS_KEY_ID.getDefaultValue());
        String accessKeySecret = configService.getByPropertyOrDefault(SmsProperties.MICROMALL_ALI_SMS_ACCESS_SECRET,
                String.class, SmsProperties.MICROMALL_ALI_SMS_ACCESS_SECRET.getDefaultValue());
        String signName = configService.getByPropertyOrDefault(SmsProperties.MICROMALL_ALI_SMS_SIGN_NAME,
                String.class, SmsProperties.MICROMALL_ALI_SMS_SIGN_NAME.getDefaultValue());
        SmsResult smsResult = new SmsResult();

        if("false".equals(accessKeyId)||"false".equals(accessKeySecret)||"false".equals(signName)){
            smsResult.setSuccessful(false);
            return smsResult;
        }

        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId,accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateId);
        /*
          NOTE：阿里云短信和腾讯云短信这里存在不一致
          腾讯云短信模板参数是数组，因此短信模板形式如 “短信参数{1}， 短信参数{2}”
          阿里云短信模板参数是JSON，因此短信模板形式如“短信参数{param1}， 短信参数{param2}”
          为了保持统一，我们假定阿里云短信里面的参数是code，code1，code2...

          如果开发者在阿里云短信申请的模板参数是其他命名，请开发者自行调整这里的代码，或者直接写死。
         */
        String templateParam = "{}";
        if(params.length == 1){
            Map<String, String> data = new HashMap<>();
            data.put("code", params[0]);
            templateParam = JacksonUtil.toJson(data);
        }
        else if(params.length > 1){
            Map<String, String> data = new HashMap<>();
            data.put("code", params[0]);
            for(int i = 1; i < params.length; i++){
                data.put("code" + i, params[i]);
            }
            templateParam = JacksonUtil.toJson(data);
        }
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            smsResult.setResult(response);
            AliyunCommonResponse aliyunCommonResponse = new Gson().fromJson(response.getData(), AliyunCommonResponse.class);
            if (aliyunCommonResponse != null) {
                if ("OK".equalsIgnoreCase(aliyunCommonResponse.Code)) {
                    smsResult.setSuccessful(true);
                } else {
                    smsResult.setSuccessful(false);
                }
            }
            return smsResult;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        smsResult.setSuccessful(false);
        return smsResult;
    }

}
