package run.micromall.micromall.core.shiro;


import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;

/**
 * @author Administrator
 */
@Getter
@Setter
public class Permission {
    private RequiresPermissions requiresPermissions;
    private RequiresPermissionsDesc requiresPermissionsDesc;
    private String api;
}
