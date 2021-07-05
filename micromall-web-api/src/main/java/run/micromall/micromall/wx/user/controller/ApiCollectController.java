package run.micromall.micromall.wx.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.user.model.entity.MicromallCollect;
import run.micromall.micromall.db.validation.Order;
import run.micromall.micromall.db.validation.Sort;
import run.micromall.micromall.service.user.service.MicromallCollectService;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * 用户收藏表
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@RestController
@RequestMapping("/collect")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class ApiCollectController {

    private final MicromallCollectService collectService;

    /**
     * 用户收藏表分页列表
     *
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/list")
    public ResponseUtil list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                             @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok(collectService.list(page,limit,sort,order));
    }
    /**
     * 添加用户收藏表
     *
     * @param micromallCollect
     * @author songhaozhi
     * @date 2021-07-05
     */
    @PostMapping("/add")
    public ResponseUtil addMicromallCollect(@RequestBody MicromallCollect micromallCollect) {
        return ResponseUtil.result(collectService.insert(micromallCollect) > 0);
    }

    /**
     * 通过ID修改用户收藏表
     *
     * @param micromallCollect
     * @author songhaozhi
     * @date 2021-07-05
     */
    @PutMapping("/update")
    public ResponseUtil update(@RequestBody MicromallCollect micromallCollect) {
        return ResponseUtil.result(collectService.updateById(micromallCollect) > 0);
    }

    /**
     * 通过ID删除用户收藏表
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-05
     */
    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteMicromallCollect(@PathVariable("id") Long id) {
        return ResponseUtil.result(collectService.deleteById(id) > 0);
    }
    /**
     * 通过ID获取用户收藏表详情
     *
     * @param id
     * @author songhaozhi
     * @date 2021-07-05
     */
    @GetMapping("/{id}")
    public ResponseUtil get(@PathVariable("id") Long id){
        return ResponseUtil.ok(collectService.selectById(id));
    }


}

