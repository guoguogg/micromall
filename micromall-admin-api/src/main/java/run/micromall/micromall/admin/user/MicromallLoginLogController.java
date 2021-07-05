package run.micromall.micromall.admin.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.user.service.MicromallLoginLogService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 用户登录日志记录
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@RestController
@RequestMapping("/admin/loginLog")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class MicromallLoginLogController {

    private final MicromallLoginLogService loginLogService;

    /**
     * 用户登录日志记录分页列表
     *
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:login:log:page")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户登录日志"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(loginLogService.list(page, limit, sort, order));
    }
}

