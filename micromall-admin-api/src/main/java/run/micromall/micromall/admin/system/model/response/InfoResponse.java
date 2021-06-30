package run.micromall.micromall.admin.system.model.response;

import lombok.Data;

import java.util.Set;

/**
 * /info接口响应值
 *
 * @author Administrator
 * @since 2021/6/30
 */
@Data
public class InfoResponse {
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色列表
     */
    private Set<String> roles;
    /**
     * 权限列表
     */
    private Set<String> perms;
}
