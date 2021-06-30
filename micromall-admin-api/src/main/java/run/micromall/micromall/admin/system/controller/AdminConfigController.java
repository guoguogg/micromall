package run.micromall.micromall.admin.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.base.Constant;
import run.micromall.micromall.service.response.ResponseUtil;
import run.micromall.micromall.service.system.service.MicroMallConfigService;

import java.util.Map;

/**
 * Admin系统配置
 */
@RestController
@RequestMapping("/admin/config")
@Validated
public class AdminConfigController {

    @Autowired
    private MicroMallConfigService configService;

    /**
     * 获取所有系统配置
     *
     * @return
     */
    @RequiresPermissions("admin:config:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "系统配置"}, button = "列表")
    @GetMapping("/list")
    public ResponseUtil list() {
        return ResponseUtil.ok(Constant.configMap);
    }


    /**
     * 保存设置
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("admin:config:save")
    @RequiresPermissionsDesc(menu = {"配置管理", "系统配置"}, button = "保存设置")
    public ResponseUtil save(@RequestParam Map<String, String> map) {
        configService.save(map);
        Map<String, String> configs = configService.selectMap();
        Constant.setConfigMap(configs);
        return ResponseUtil.ok();
    }


}
