package run.micromall.micromall.db.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Long ID请求参数
 *
 * @author Administrator
 * @since 2021/1/20
 */
@Getter
@Setter
public class IdRequest {
    /**
     * 操作的Id
     * 比如操作标签的删除就是标签ID，角色的就是角色ID
     */
    @NotNull(message = "参数错误")
    private Long id;
}
