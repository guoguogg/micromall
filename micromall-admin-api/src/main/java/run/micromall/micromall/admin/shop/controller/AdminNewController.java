package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.base.ListIdRequest;
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;
import run.micromall.micromall.db.shop.model.entity.MicromallNew;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallNewService;
import run.micromall.micromall.service.utils.ResponseUtil;

import javax.validation.Valid;

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
     * @param name  商品名称
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:new:page")
    @RequiresPermissionsDesc(menu = {"商品管理", "新品管理"}, button = "新品列表")
    public ResponseUtil<MicromallGoods> list(String name, @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit,
                                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(micromallNewService.list(name, page, limit, sort, order));
    }

    /**
     * 添加新品首发商品关联
     *
     * @param request
     * @apiNote 可以批量添加
     * @author songhaozhi
     * @date 2021-07-09
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:new:add")
    @RequiresPermissionsDesc(menu = {"商品管理", "新品管理"}, button = "添加新品")
    public ResponseUtil addMicromallNew(@Valid @RequestBody ListIdRequest request) {
        return micromallNewService.addMicromallNew(request);
    }

    /**
     * 删除新品首发商品关联
     *
     * @param request
     * @apiNote 可以批量删除
     * @author songhaozhi
     * @date 2021-07-09
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:new:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "新品管理"}, button = "删除新品")
    public ResponseUtil delete(@Valid @RequestBody ListIdRequest request) {
        return micromallNewService.delete(request);
    }

}

