package run.micromall.micromall.admin.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.core.shiro.ShiroUtil;
import run.micromall.micromall.db.base.IdParam;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.system.service.MicroMallAdminService;
import run.micromall.micromall.service.utils.ResponseUtil;
import run.micromall.micromall.service.utils.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 后台用户
 */
@RestController
@RequestMapping("/admin/admin")
@Validated
public class AdminAdminController {

    @Autowired
    private MicroMallAdminService adminService;

    /**
     * 后台用户列表
     *
     * @param username
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("admin:admin:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "查询")
    @GetMapping("/list")
    public ResponseUtil list(String username,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort(accepts = {"add_time"}) @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(adminService.list(username, page, limit, sort, order));
    }

    /**
     * 添加
     *
     * @param admin
     * @return
     */
    @RequiresPermissions("admin:admin:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@Validated @RequestBody MicroMallAdmin admin) {
        String username = admin.getUsername();
        List<MicroMallAdmin> adminList = adminService.findAdmin(username);
        if (adminList.size() > 0) {
            return ResponseUtil.fail("管理员已经存在");
        }

        String rawPassword = admin.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        adminService.add(admin);
        return ResponseUtil.ok(admin);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:admin:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "详情")
    @GetMapping("/read")
    public ResponseUtil read(@NotNull Long id) {
        MicroMallAdmin admin = adminService.findById(id);
        return ResponseUtil.ok(admin);
    }

    /**
     * 编辑
     *
     * @param admin
     * @return
     */
    @RequiresPermissions("admin:admin:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@Validated @RequestBody MicroMallAdmin admin) {
        Long anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return ResponseUtil.paramError();
        }

        // 不允许管理员通过编辑接口修改密码
        admin.setPassword(null);

        if (adminService.updateById(admin) == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(admin);
    }

    /**
     * 删除
     *
     * @param param
     * @return
     */
    @RequiresPermissions("admin:admin:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody IdParam param) {
        Long anotherAdminId = param.getId();
        if (anotherAdminId == null) {
            return ResponseUtil.paramError();
        }

        // 管理员不能删除自身账号
        MicroMallAdmin currentAdmin = ShiroUtil.getCurrentUser();
        if (currentAdmin.getId().equals(anotherAdminId)) {
            return ResponseUtil.fail("管理员不能删除自己账号");
        }
        if (anotherAdminId == 1) {
            return ResponseUtil.fail("超级管理员账号不能删除");
        }
        adminService.deleteById(anotherAdminId);
        return ResponseUtil.ok();
    }
}
