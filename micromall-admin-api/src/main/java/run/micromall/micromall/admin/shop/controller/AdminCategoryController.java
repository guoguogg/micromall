package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallCategory;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallCategoryService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 商品分类表
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Slf4j
@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminCategoryController {

    private final MicromallCategoryService categoryService;

    /**
     * 商品分类表分页列表
     *
     * @author songhaozhi
     * @date 2021-06-30
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:category:page")
    @RequiresPermissionsDesc(menu = {"商城管理", "商品分类管理"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(categoryService.list(page, limit, sort, order));
    }

    /**
     * 添加商品分类表
     *
     * @param category
     * @author songhaozhi
     * @date 2021-06-30
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:category:add")
    @RequiresPermissionsDesc(menu = {"商城管理", "商品分类管理"}, button = "添加商品分类")
    public ResponseUtil addMicromallCategory(@RequestBody MicromallCategory category) {
        return ResponseUtil.result(categoryService.insert(category) > 0);
    }

    /**
     * 通过ID修改商品分类表
     *
     * @param category
     * @author songhaozhi
     * @date 2021-06-30
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:category:update")
    @RequiresPermissionsDesc(menu = {"商城管理", "商品分类管理"}, button = "修改商品分类")
    public ResponseUtil update(@RequestBody MicromallCategory category) {
        return ResponseUtil.result(categoryService.updateById(category) > 0);
    }

    /**
     * 通过ID删除商品分类表
     *
     * @param id
     * @author songhaozhi
     * @date 2021-06-30
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:category:delete")
    @RequiresPermissionsDesc(menu = {"商城管理", "商品分类管理"}, button = "删除商品分类")
    public ResponseUtil deleteMicromallCategory(@PathVariable("id") Long id) {
        return ResponseUtil.result(categoryService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取商品分类表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-06-30
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:category:info")
    @RequiresPermissionsDesc(menu = {"商城管理", "商品分类管理"}, button = "获取商品分类详情")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(categoryService.selectById(id));
    }


}

