/*
 * micromall https://github.com/micromall-team/micromall
 *
 * Copyright (C) 2021-2021 micromall
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package run.micromall.micromall.admin.controller.system;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import run.micromall.micromall.core.annotation.RequiresPermissionsDesc;
import run.micromall.micromall.db.base.IdParam;
import run.micromall.micromall.db.system.model.entity.MicroMallStorage;
import run.micromall.micromall.service.response.ResponseUtil;
import run.micromall.micromall.service.system.service.MicroMallStorageService;
import run.micromall.micromall.service.system.storage.UploadResult;

/**
 * 文件相关接口
 *
 * @author songhaozhi
 * @since 2021/1/14
 */
@RequestMapping("/admin/storage")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MicroMallStorageController {

    private final MicroMallStorageService storageService;
    /**
     * 附件分页列表
     *
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getStorageList")
    @RequiresPermissions("admin:storage:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "附件管理"}, button = "附件查询")
    public ResponseUtil<MicroMallStorage> list(String name,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer limit) {
        return ResponseUtil.ok(storageService.list(name, page, limit));
    }

    /**
     * 附件删除
     *
     * @param param
     * @return
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("admin:storage:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "附件管理"}, button = "附件删除")
    public ResponseUtil<MicroMallStorage> delete(IdParam param) {
        if (ObjectUtil.isNull(param)) {
            return ResponseUtil.paramError();
        }
        return ResponseUtil.ok(storageService.delete(param.getId()));
    }
    /**
     * 上传文件
     *
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public ResponseUtil<UploadResult> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseUtil.paramError();
        }
        return storageService.upload(file);
    }

}
