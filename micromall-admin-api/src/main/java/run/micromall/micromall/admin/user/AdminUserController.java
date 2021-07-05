package run.micromall.micromall.admin.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.user.model.entity.MicromallUser;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.user.service.MicromallUserService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 用户信息表
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminUserController {

    private final MicromallUserService userService;

    /**
     * 用户信息表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:user:page")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户管理"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(userService.list(page,limit,sort,order));
    }
    
    /**
     * 通过ID修改用户信息表
     *
     * @param micromallUser
     * @author songhaozhi
     * @date 2021-07-05
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:user:update")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户管理"}, button = "请修改")
    public ResponseUtil update(@RequestBody MicromallUser micromallUser) {
        return ResponseUtil.result(userService.updateById(micromallUser) > 0);
    }

    /**
     * 通过ID获取用户信息表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:user:info")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户管理"}, button = "请修改")
    public ResponseUtil get(@PathVariable("id") Long id){
        return ResponseUtil.ok(userService.selectById(id));
    }


}

