package run.micromall.micromall.core.shiro;

import lombok.Data;

import java.util.List;

@Data
public class PermVo {
    /**
     * 权限标识
     */
    private String id;
    /**
     * 菜单
     */
    private String label;
    /**
     * API地址
     */
    private String api;
    private List<PermVo> children;

}