package run.micromall.micromall.core.shiro;


import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;
import run.micromall.micromall.service.system.service.MicroMallAdminService;
import run.micromall.micromall.service.system.service.MicroMallPermissionService;
import run.micromall.micromall.service.system.service.MicroMallRoleService;
import run.micromall.micromall.service.utils.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
public class AdminAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private MicroMallAdminService adminService;
    @Autowired
    private MicroMallRoleService roleService;
    @Autowired
    private MicroMallPermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        MicroMallAdmin admin = (MicroMallAdmin) getAvailablePrincipal(principals);
        Long[] roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if (StrUtil.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StrUtil.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }

        List<MicroMallAdmin> adminList = adminService.findAdmin(username);
        Assert.state(adminList.size() < 2, "同一个用户名存在两个账户");
        if (adminList.size() == 0) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        MicroMallAdmin admin = adminList.get(0);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, admin.getPassword())) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }

        return new SimpleAuthenticationInfo(admin, password, getName());
    }

}
