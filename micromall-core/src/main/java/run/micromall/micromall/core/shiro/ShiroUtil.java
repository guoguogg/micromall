package run.micromall.micromall.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;

import java.util.Arrays;
import java.util.List;

/**
 * ShiroUtil
 *
 * @author Administrator
 */
public class ShiroUtil {
    /**
     * 获取当前用户
     *
     * @return
     */
    public static MicroMallAdmin getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        return (MicroMallAdmin) currentUser.getPrincipal();
    }

    /**
     * 当前登录用户角色列表
     *
     * @return
     */
    public static List<Long> getCurrentUserRoles() {
        Subject currentUser = SecurityUtils.getSubject();
        MicroMallAdmin admin = (MicroMallAdmin) currentUser.getPrincipal();
        return Arrays.asList(admin.getRoleIds());
    }


}