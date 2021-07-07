package run.micromall.micromall.db.system.properties;

/**
 * 短信模板配置
 *
 * @author Administrator
 * @since 2021/7/7
 */
public enum SmsProperties implements Properties {
    /**
     * 配置使用的是哪家短信服务
     */
    MICROMALL_ACTIVE("micromall_active", String.class, "false"),
    /**
     * json形式字符串
     *
     * List<Map<String, String>>
     */
    MICROMALL_SMS_TEMPLATE("micromall_sms_template", String.class, "[{\"templateId\":\"156349\",\"name\":\"paySucceed\"},{\"templateId\":\"156433\",\"name\":\"captcha\"},{\"templateId\":\"158002\",\"name\":\"ship\"},{\"templateId\":\"159447\",\"name\":\"refund\"}]"),
    /**
     * 阿里短信服务-accessKeyId
     */
    MICROMALL_ALI_SMS_ACCESS_KEY_ID("micromall_ali_sms_access_key_id", String.class, "false"),
    /**
     * 阿里短信服务-accessSecret
     */
    MICROMALL_ALI_SMS_ACCESS_SECRET("micromall_ali_sms_access_secret", String.class, "false"),
    /**
     * 阿里短信服务-签名
     */
    MICROMALL_ALI_SMS_SIGN_NAME("micromall_ali_sms_sign_name", String.class, "false");
    private final String value;
    private final Class<?> type;
    private final String defaultValue;

    SmsProperties(String value, Class<?> type, String defaultValue) {
        this.defaultValue = defaultValue;
        this.value = value;
        this.type = type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
