package run.micromall.micromall.service.system.model.request;

import lombok.Data;

import java.util.List;

/**
 * 修改权限请求参数
 *
 * @author Administrator
 * @since 2021/3/15
 */
@Data
public class UpdatePermissionsRequest {
    private Long roleId;

    private List<String> permissions;
}
