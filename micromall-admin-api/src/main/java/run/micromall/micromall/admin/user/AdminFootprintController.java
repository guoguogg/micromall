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
import run.micromall.micromall.service.user.service.MicromallFootprintService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 用户足迹表
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@RestController
@RequestMapping("/admin/footprint")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminFootprintController {

    private final MicromallFootprintService footprintService;

    /**
     * 用户足迹表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:footprint:page")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户足迹"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(footprintService.list(page, limit, sort, order));
    }
}

