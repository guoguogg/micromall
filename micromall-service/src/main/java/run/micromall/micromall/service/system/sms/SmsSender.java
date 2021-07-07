package run.micromall.micromall.service.system.sms;

public interface SmsSender {

    /**
     * 发送手机消息
     *
     * @param mobile     接收手机号
     * @param templateId 模板ID
     * @param params     通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
     * @return
     */
    SmsResult sendCode(String mobile, String templateId, String[] params);

}
