package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallSpec;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallSpecService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 规格表
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@RestController
@RequestMapping("/admin/spec")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminSpecController {

    private final MicromallSpecService specService;

    /**
     * 规格表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-08
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:spec:page")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格管理"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(specService.list(page, limit, sort, order));
    }

    /**
     * 添加规格表
     *
     * @param micromallSpec
     * @author songhaozhi
     * @date 2021-07-08
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:spec:add")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格管理"}, button = "添加")
    public ResponseUtil addMicromallSpec(@RequestBody MicromallSpec micromallSpec) {
        return ResponseUtil.result(specService.insert(micromallSpec) > 0);
    }

    /**
     * 通过ID修改规格表
     *
     * @param micromallSpec
     * @author songhaozhi
     * @date 2021-07-08
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:spec:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格管理"}, button = "修改")
    public ResponseUtil update(@RequestBody MicromallSpec micromallSpec) {
        return ResponseUtil.result(specService.updateById(micromallSpec) > 0);
    }

    /**
     * 通过ID删除规格表
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-08
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:spec:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格管理"}, button = "删除")
    public ResponseUtil deleteMicromallSpec(@PathVariable("id") Long id) {
        return ResponseUtil.result(specService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取规格表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-08
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:spec:info")
    @RequiresPermissionsDesc(menu = {"商品管理", "规格管理"}, button = "获取规格表详情")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(specService.selectById(id));
    }


}

