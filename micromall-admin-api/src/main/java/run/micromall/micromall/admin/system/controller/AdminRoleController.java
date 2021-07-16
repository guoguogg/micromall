package run.micromall.micromall.admin.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.service.system.model.request.UpdatePermissionsRequest;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.core.shiro.PermVo;
import run.micromall.micromall.core.shiro.Permission;
import run.micromall.micromall.core.shiro.PermissionUtil;
import run.micromall.micromall.db.base.IdRequest;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;
import run.micromall.micromall.db.system.model.entity.MicroMallPermission;
import run.micromall.micromall.db.system.model.entity.MicroMallRole;
import run.micromall.micromall.db.validation.AddGroup;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.db.validation.UpdateGroup;
import run.micromall.micromall.service.system.service.MicroMallAdminService;
import run.micromall.micromall.service.system.service.MicroMallPermissionService;
import run.micromall.micromall.service.system.service.MicroMallRoleService;
import run.micromall.micromall.service.utils.ResponseUtil;

import java.util.*;


/**
 * admin角色管理
 *
 * @author songhaozhi
 * @since 2021-01-19
 */
@Slf4j
@RestController
@RequestMapping("/admin/role")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminRoleController {

    private final MicroMallRoleService roleService;
    private final MicroMallPermissionService permissionService;
    private final MicroMallAdminService adminService;

    /**
     * 角色列表查询
     *
     * @param name  角色名称
     * @param page
     * @param limit
     * @param sort  排序字段
     * @param order 降序倒序字段
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("admin:role:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色查询")
    public ResponseUtil<MicroMallRole> getRoleList(String name,
                                                   @RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer limit,
                                                   @Sort(accepts = {"add_time"}) @RequestParam(defaultValue = "add_time") String sort,
                                                   @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(roleService.getRoleList(name, page, limit, sort, order));
    }

    /**
     * 删除
     *
     * @param role
     * @return
     */
    @RequiresPermissions("admin:role:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色删除")
    @PostMapping("/delete")
    public ResponseUtil delete(@RequestBody MicroMallRole role) {
        Long id = role.getId();
        if (id == null) {
            return ResponseUtil.paramError();
        }

        // 如果当前角色所对应管理员仍存在，则拒绝删除角色。
        List<MicroMallAdmin> adminList = adminService.all();
        for (MicroMallAdmin admin : adminList) {
            Long[] roleIds = admin.getRoleIds();
            for (Long roleId : roleIds) {
                if (id.equals(roleId)) {
                    return ResponseUtil.fail("当前角色存在管理员，不能删除");
                }
            }
        }
        roleService.deleteById(id);
        return ResponseUtil.ok();
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @GetMapping("/options")
    public ResponseUtil options() {
        List<MicroMallRole> roleList = roleService.queryAll();

        List<Map<String, Object>> options = new ArrayList<>(roleList.size());
        for (MicroMallRole role : roleList) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", role.getId());
            option.put("label", role.getName());
            options.add(option);
        }
        return ResponseUtil.ok(options);
    }

    /**
     * 角色添加
     *
     * @param role
     * @return
     */
    @RequiresPermissions("admin:role:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色添加")
    @PostMapping("/create")
    public ResponseUtil<MicroMallRole> create(@Validated({AddGroup.class}) @RequestBody MicroMallRole role) {
        if (roleService.checkExist(role.getName())) {
            return ResponseUtil.fail("角色已经存在");
        }
        roleService.add(role);
        return ResponseUtil.ok(role);
    }

    /**
     * 角色修改
     *
     * @param role
     * @return
     */
    @RequiresPermissions("admin:role:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色编辑")
    @PostMapping("/update")
    public ResponseUtil update(@Validated({UpdateGroup.class}) @RequestBody MicroMallRole role) {
        roleService.updateById(role);
        return ResponseUtil.ok();
    }

    /**
     * 获取角色详情
     *
     * @param param id
     * @return
     */
    @RequiresPermissions("admin:role:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色详情")
    @GetMapping("/read")
    public ResponseUtil<MicroMallRole> read(@Validated IdRequest param) {
        MicroMallRole role = roleService.findById(param.getId());
        return ResponseUtil.ok(role);
    }

    /**
     * 管理员的权限情况
     *
     * @return 系统所有权限列表和管理员已分配权限
     */
    @RequiresPermissions("admin:role:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限详情")
    @GetMapping("/permissions")
    public ResponseUtil getPermissions(@Validated IdRequest param) {
        List<PermVo> systemPermissions = getSystemPermissions();
        Set<String> assignedPermissions = getAssignedPermissions(param.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissions);
        data.put("assignedPermissions", assignedPermissions);
        return ResponseUtil.ok(data);
    }

    /**
     * 更新管理员的权限
     *
     * @param param
     * @return
     */
    @RequiresPermissions("admin:role:permission:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限变更")
    @PostMapping("/permissions")
    public ResponseUtil updatePermissions(@RequestBody UpdatePermissionsRequest param) {
        Long roleId = param.getRoleId();
        List<String> permissions = param.getPermissions();
        if (roleId == null || permissions == null) {
            return ResponseUtil.paramError();
        }

        // 如果修改的角色是超级权限，则拒绝修改。
        if (permissionService.checkSuperPermission(roleId)) {
            return ResponseUtil.fail("当前角色的超级权限不能变更");
        }

        // 先删除旧的权限，再更新新的权限
        permissionService.deleteByRoleId(roleId);
        for (String permission : permissions) {
            MicroMallPermission MicroMallPermission = new MicroMallPermission();
            MicroMallPermission.setRoleId(roleId);
            MicroMallPermission.setPermission(permission);
            permissionService.add(MicroMallPermission);
        }
        return ResponseUtil.ok();
    }

    private Set<String> getAssignedPermissions(Long roleId) {
        // 这里需要注意的是，如果存在超级权限*，那么这里需要转化成当前所有系统权限。
        // 之所以这么做，是因为前端不能识别超级权限，所以这里需要转换一下。
        Set<String> assignedPermissions = null;
        if (permissionService.checkSuperPermission(roleId)) {
            getSystemPermissions();
            assignedPermissions = systemPermissionsString;
        } else {
            assignedPermissions = permissionService.queryByRoleId(roleId);
        }

        return assignedPermissions;
    }

    @Autowired
    private ApplicationContext context;
    private List<PermVo> systemPermissions = null;
    private Set<String> systemPermissionsString = null;

    private List<PermVo> getSystemPermissions() {
        final String basicPackage = "com.micromall.micromall.admin";
        if (systemPermissions == null) {
            List<Permission> permissions = PermissionUtil.listPermission(context, basicPackage);
            systemPermissions = PermissionUtil.listPermVo(permissions);
            systemPermissionsString = PermissionUtil.listPermissionString(permissions);
        }
        return systemPermissions;
    }

}

