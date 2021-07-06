package run.micromall.micromall.admin.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.system.model.entity.MicromallFeedback;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.system.service.MicromallFeedbackService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 意见反馈表
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@RestController
@RequestMapping("/admin/feedback")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class AdminFeedbackController {

    private final MicromallFeedbackService feedbackService;

    /**
     * 意见反馈表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/list")
    @RequiresPermissions("micromall:feedback:page")
    @RequiresPermissionsDesc(menu = {"系统管理", "用户反馈"}, button = "列表")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(feedbackService.list(page,limit,sort,order));
    }
}

