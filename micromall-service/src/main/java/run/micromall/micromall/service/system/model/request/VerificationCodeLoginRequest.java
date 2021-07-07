package run.micromall.micromall.service.system.model.request;

import lombok.Data;

/**
 * 验证码登录请求参数
 *
 * @author Administrator
 * @since 2021/7/6
 */
@Data
public class VerificationCodeLoginRequest {
    private String mobile;

    private String code;
}
