package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.Log;
import run.micromall.micromall.db.base.ListIdRequest;
import run.micromall.micromall.db.validation.AddGroup;
import run.micromall.micromall.db.validation.UpdateGroup;
import run.micromall.micromall.service.shop.model.request.CreateGoodsRequest;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.model.request.GoodsAttribute;
import run.micromall.micromall.service.shop.service.MicromallGoodsService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 商品信息表
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Slf4j
@RestController
@RequestMapping("/admin/goods")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminGoodsController {

    private final MicromallGoodsService goodsService;

    /**
     * 商品信息表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-04
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:goods:page")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "商品列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(goodsService.list(page, limit, sort, order));
    }

    /**
     * 添加商品信息
     *
     * @param goods
     * @author songhaozhi
     * @date 2021-07-04
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:goods:add")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "添加商品")
    @Log(name = "商品管理-添加商品")
    public ResponseUtil createGoods(@Validated(value = AddGroup.class) @RequestBody CreateGoodsRequest goods) {
        return ResponseUtil.ok(goodsService.createGoods(goods));
    }

    /**
     * 添加商品参数信息
     *
     * @param goodsAttribute
     * @author songhaozhi
     * @date 2021-07-04
     */
    @PostMapping("/addAttribute")
    @RequiresPermissions("micromall:goods:add:attribute")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "添加商品参数")
    @Log(name = "商品管理-添加商品参数")
    public ResponseUtil addAttribute(@Validated @RequestBody GoodsAttribute goodsAttribute) {
        return goodsService.addAttribute(goodsAttribute);
    }

    /**
     * 修改商品参数信息
     *
     * @param attribute
     * @author songhaozhi
     * @date 2021-07-04
     */
    @PutMapping("/updateAttribute")
    @RequiresPermissions("micromall:goods:update:attribute")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "修改商品参数")
    @Log(name = "商品管理-修改商品参数")
    public ResponseUtil updateAttribute(@Validated @RequestBody GoodsAttribute.Attribute attribute) {
        return goodsService.updateAttribute(attribute);
    }

    /**
     * 删除商品参数信息
     *
     * @param request
     * @author songhaozhi
     * @date 2021-07-04
     */
    @DeleteMapping("/deleteAttribute")
    @RequiresPermissions("micromall:goods:delete:attribute")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "删除商品参数")
    @Log(name = "商品管理-删除商品参数")
    public ResponseUtil deleteAttribute(@Validated @RequestBody ListIdRequest request) {
        return goodsService.deleteAttribute(request);
    }


    /**
     * 通过ID修改商品信息
     *
     * @param goods
     * @author songhaozhi
     * @date 2021-07-04
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:goods:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "修改商品")
    public ResponseUtil update(@Validated(value = UpdateGroup.class) @RequestBody CreateGoodsRequest goods) {
        return goodsService.updateGoods(goods);
    }

    /**
     * 通过ID删除商品信息
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-04
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:goods:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "删除商品")
    public ResponseUtil deleteMicromallGoods(@PathVariable("id") Long id) {
        return ResponseUtil.result(goodsService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取商品信息表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-04
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:goods:info")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品列表"}, button = "获取商品信息")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(goodsService.selectById(id));
    }


}

