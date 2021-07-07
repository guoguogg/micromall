package run.micromall.micromall.service.system.sms;

public interface SmsSender {

    SmsResult sendCode(String mobile, String content);

}
