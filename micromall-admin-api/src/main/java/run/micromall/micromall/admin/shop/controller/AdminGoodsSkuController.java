package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsSku;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallGoodsSkuService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 商品SKU表
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@RestController
@RequestMapping("/admin/goodsSku")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminGoodsSkuController {

    private final MicromallGoodsSkuService goodsSkuService;

    /**
     * 商品SKU表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-08
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:goods:sku:page")
    @RequiresPermissionsDesc(menu = {"商品管理", "SKU管理"}, button = "SKU列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(goodsSkuService.list(page, limit, sort, order));
    }

    /**
     * 添加商品SKU表
     *
     * @param micromallGoodsSku
     * @author songhaozhi
     * @date 2021-07-08
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:goods:sku:add")
    @RequiresPermissionsDesc(menu = {"商品管理", "SKU管理"}, button = "添加")
    public ResponseUtil addMicromallGoodsSku(@RequestBody MicromallGoodsSku micromallGoodsSku) {
        return ResponseUtil.result(goodsSkuService.insert(micromallGoodsSku) > 0);
    }

    /**
     * 通过ID修改商品SKU表
     *
     * @param micromallGoodsSku
     * @author songhaozhi
     * @date 2021-07-08
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:goods:sku:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "SKU管理"}, button = "修改")
    public ResponseUtil update(@RequestBody MicromallGoodsSku micromallGoodsSku) {
        return ResponseUtil.result(goodsSkuService.updateById(micromallGoodsSku) > 0);
    }

    /**
     * 通过ID删除商品SKU表
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-08
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:goods:sku:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "SKU管理"}, button = "删除")
    public ResponseUtil deleteMicromallGoodsSku(@PathVariable("id") Long id) {
        return ResponseUtil.result(goodsSkuService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取商品SKU表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-08
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:goods:sku:info")
    @RequiresPermissionsDesc(menu = {"商品管理", "SKU管理"}, button = "获取详情")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(goodsSkuService.selectById(id));
    }


}

