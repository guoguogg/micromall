package run.micromall.micromall.admin.system.controller;

import com.power.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.admin.system.model.request.LoginRequest;
import run.micromall.micromall.admin.system.model.response.InfoResponse;
import run.micromall.micromall.admin.system.model.response.LoginResponse;
import run.micromall.micromall.core.shiro.Permission;
import run.micromall.micromall.core.shiro.PermissionUtil;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;
import run.micromall.micromall.service.system.service.MicroMallAdminService;
import run.micromall.micromall.service.system.service.MicroMallPermissionService;
import run.micromall.micromall.service.system.service.MicroMallRoleService;
import run.micromall.micromall.service.utils.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * admin认证相关
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/auth")
@Slf4j
public class AdminAuthController {

    @Autowired
    private MicroMallAdminService adminService;
    @Autowired
    private MicroMallRoleService roleService;
    @Autowired
    private MicroMallPermissionService permissionService;

    /**
     * 登录
     *
     * @param loginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseUtil login(@Validated @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            return ResponseUtil.fail("用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            return ResponseUtil.fail("用户帐号已锁定不可用");
        } catch (AuthenticationException ae) {
            return ResponseUtil.fail("认证失败");
        }
        currentUser = SecurityUtils.getSubject();
        MicroMallAdmin admin = (MicroMallAdmin) currentUser.getPrincipal();
        adminService.updateLastLoginIpAndLastLoginTimeById(IpUtil.getIpAddr(request),LocalDateTime.now(),admin.getId());
        //设置session过期时间为一天
        currentUser.getSession().setTimeout(7200000L);

        LoginResponse response = new LoginResponse();
        response.setNickName(admin.getUsername());
        response.setAvatar(admin.getAvatar());
        response.setToken(currentUser.getSession().getId().toString());
        return ResponseUtil.ok(response);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/logout")
    public Object logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseUtil.ok();
    }

    @RequiresAuthentication
    @GetMapping("/not/authorized")
    public ResponseUtil notAuthorized() {
        return ResponseUtil.notAuthorized();
    }

    @RequiresAuthentication
    @GetMapping("/not/login")
    public ResponseUtil notLogin() {
        return ResponseUtil.notLogin();
    }
    /**
     * 获取登录用户信息
     *
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/info")
    public ResponseUtil info() {
        Subject currentUser = SecurityUtils.getSubject();
        MicroMallAdmin admin = (MicroMallAdmin) currentUser.getPrincipal();
        Long[] roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);

        InfoResponse response = new InfoResponse();
        response.setNickName(admin.getUsername());
        response.setAvatar(admin.getAvatar());
        response.setRoles(roles);
        // NOTE
        // 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
        response.setPerms(toApi(permissions));
        return ResponseUtil.ok(response);
    }

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private HashSet<String> toApi(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>();
            final String basicPackage = "com.micromall.micromall.admin";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        HashSet<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);
            if ("*".equals(perm)) {
                apis.clear();
                apis.add("*");
                return apis;
            }
        }
        return apis;
    }
}
