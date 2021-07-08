package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallSpecValue;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.system.service.MicromallSpecValueService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 规格值
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@RestController
@RequestMapping("/admin/specValue")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminSpecValueController {

    private final MicromallSpecValueService specValueService;

    /**
     * 规格值分页列表
     *
     * @author songhaozhi
     * @date 2021-07-08
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:spec:value:page")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格值管理"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(specValueService.list(page, limit, sort, order));
    }

    /**
     * 添加规格值
     *
     * @param micromallSpecValue
     * @author songhaozhi
     * @date 2021-07-08
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:spec:value:add")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格值管理"}, button = "添加")
    public ResponseUtil addMicromallSpecValue(@RequestBody MicromallSpecValue micromallSpecValue) {
        return ResponseUtil.result(specValueService.insert(micromallSpecValue) > 0);
    }

    /**
     * 通过ID修改规格值
     *
     * @param micromallSpecValue
     * @author songhaozhi
     * @date 2021-07-08
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:spec:value:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格值管理"}, button = "修改")
    public ResponseUtil update(@RequestBody MicromallSpecValue micromallSpecValue) {
        return ResponseUtil.result(specValueService.updateById(micromallSpecValue) > 0);
    }

    /**
     * 通过ID删除规格值
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-08
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:spec:value:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格值管理"}, button = "删除")
    public ResponseUtil deleteMicromallSpecValue(@PathVariable("id") Long id) {
        return ResponseUtil.result(specValueService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取规格值详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-08
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:spec:value:info")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格值管理"}, button = "获取规格值详情")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(specValueService.selectById(id));
    }


}

