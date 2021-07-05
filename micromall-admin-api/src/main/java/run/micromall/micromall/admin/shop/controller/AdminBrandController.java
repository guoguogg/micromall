package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallBrand;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallBrandService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 品牌商
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Slf4j
@RestController
@RequestMapping("/admin/brand")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminBrandController {

    private final MicromallBrandService brandService;

    /**
     * 品牌商分页列表
     *
     * @author songhaozhi
     * @date 2021-06-30
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:brand:page")
    @RequiresPermissionsDesc(menu = {"商城管理", "品牌商管理"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(brandService.list(page, limit, sort, order));
    }

    /**
     * 添加品牌商
     *
     * @param brand
     * @author songhaozhi
     * @date 2021-06-30
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:brand:add")
    @RequiresPermissionsDesc(menu = {"商城管理", "品牌商管理"}, button = "添加品牌商")
    public ResponseUtil addMicromallBrand(@RequestBody MicromallBrand brand) {
        return ResponseUtil.result(brandService.insert(brand) > 0);
    }

    /**
     * 通过ID修改品牌商
     *
     * @param brand
     * @author songhaozhi
     * @date 2021-06-30
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:brand:update")
    @RequiresPermissionsDesc(menu = {"商城管理", "品牌商管理"}, button = "修改品牌商")
    public ResponseUtil update(@RequestBody MicromallBrand brand) {
        return ResponseUtil.result(brandService.updateById(brand) > 0);
    }

    /**
     * 通过ID删除品牌商
     *
     * @param id
     * @author songhaozhi
     * @date 2021-06-30
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:brand:delete")
    @RequiresPermissionsDesc(menu = {"商城管理", "品牌商管理"}, button = "删除品牌商")
    public ResponseUtil deleteMicromallBrand(@PathVariable("id") Long id) {
        return ResponseUtil.result(brandService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取品牌商详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-06-30
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:brand:info")
    @RequiresPermissionsDesc(menu = {"商城管理", "品牌商管理"}, button = "获取详情")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(brandService.selectById(id));
    }


}

