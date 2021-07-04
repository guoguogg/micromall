package run.micromall.micromall.service.system.model.response;

import lombok.Data;

/**
 * 登录响应参数
 *
 * @author Administrator
 * @since 2021/6/30
 */
@Data
public class LoginResponse {
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户登录token
     */
    private String token;
}



