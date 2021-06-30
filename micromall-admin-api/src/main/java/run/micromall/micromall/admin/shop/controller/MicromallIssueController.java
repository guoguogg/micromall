package run.micromall.micromall.admin.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.shop.model.entity.MicromallIssue;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.shop.service.MicromallIssueService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 常见问题表
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Slf4j
@RestController
@RequestMapping("/admin/issue")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class MicromallIssueController {

    private final MicromallIssueService issueService;

    /**
     * 常见问题表分页列表
     *
     * @author songhaozhi
     * @date 2021-06-30
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:issue:page")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题管理"}, button = "常见问题列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(issueService.list(page, limit, sort, order));
    }

    /**
     * 添加常见问题表
     *
     * @param issue
     * @author songhaozhi
     * @date 2021-06-30
     */
    @PostMapping("/add")
    @RequiresPermissions("micromall:issue:add")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题管理"}, button = "添加常见问题")
    public ResponseUtil addMicromallIssue(@RequestBody MicromallIssue issue) {
        return ResponseUtil.result(issueService.insert(issue) > 0);
    }

    /**
     * 通过ID修改常见问题表
     *
     * @param issue
     * @author songhaozhi
     * @date 2021-06-30
     */
    @PutMapping("/update")
    @RequiresPermissions("micromall:issue:update")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题管理"}, button = "修改常见问题")
    public ResponseUtil update(@RequestBody MicromallIssue issue) {
        return ResponseUtil.result(issueService.updateById(issue) > 0);
    }

    /**
     * 通过ID删除常见问题表
     *
     * @param id
     * @author songhaozhi
     * @date 2021-06-30
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("micromall:issue:delete")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题管理"}, button = "删除常见问题")
    public ResponseUtil deleteMicromallIssue(@PathVariable("id") Long id) {
        return ResponseUtil.result(issueService.deleteById(id) > 0);
    }

    /**
     * 通过ID获取常见问题表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-06-30
     */
    @GetMapping("/{id}")
    @RequiresPermissions("micromall:issue:info")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题管理"}, button = "获取常见问题详情")
    public ResponseUtil get(@PathVariable("id") Long id) {
        return ResponseUtil.ok(issueService.selectById(id));
    }


}

