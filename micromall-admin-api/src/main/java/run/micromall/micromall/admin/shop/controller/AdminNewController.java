package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallNew;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallNewService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 新品首发商品关联
 *
 * @author songhaozhi
 * @since 2021-07-09
 */
@Slf4j
@RestController
@RequestMapping("/admin/new")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminNewController {

    private final MicromallNewService micromallNewService;

    /**
     * 新品首发商品关联分页列表
     *
     * @author songhaozhi
     * @date 2021-07-09
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:new:page")
    @RequiresPermissionsDesc(menu = {"商品管理", "新品管理"}, button = "新品列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(micromallNewService.list(page, limit, sort, order));
    }

    /**
     * 添加新品首发商品关联
     *
     * @param micromallNew
     * @author songhaozhi
     * @date 2021-07-09
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:new:add")
    @RequiresPermissionsDesc(menu = {"商品管理", "新品管理"}, button = "添加新品")
    public ResponseUtil addMicromallNew(@RequestBody MicromallNew micromallNew) {
        return ResponseUtil.result(micromallNewService.insert(micromallNew) > 0);
    }

    /**
     * 删除新品首发商品关联
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-09
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:new:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "新品管理"}, button = "删除新品")
    public ResponseUtil deleteMicromallNew(@PathVariable("id") Long id) {
        return ResponseUtil.result(micromallNewService.deleteById(id) > 0);
    }

}

