package run.micromall.micromall.admin.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.service.system.service.MicromallLogService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 日志记录
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Slf4j
@RestController
@RequestMapping("/admin/log")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminLogController {

    private final MicromallLogService micromallLogService;

    /**
     * 日志记录分页列表
     *
     * @author songhaozhi
     * @date 2021-07-04
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:log:page")
    @RequiresPermissionsDesc(menu = {"系统管理", "日志管理"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        return ResponseUtil.ok(micromallLogService.list(page,limit));
    }
}

