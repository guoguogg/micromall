package run.micromall.micromall.service.system.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 *
 * @author Administrator
 * @since 2021/1/19
 */
@Data
public class LoginRequest {
    @NotBlank(message = "参数错误")
    private String username;
    @NotBlank(message = "参数错误")
    private String password;
}
