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
package run.micromall.micromall.service.system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import run.micromall.micromall.db.system.properties.StorageProperties;
import run.micromall.micromall.service.response.ResponseUtil;
import run.micromall.micromall.service.system.storage.UploadFactory;
import run.micromall.micromall.service.system.storage.UploadResult;

/**
 * 系统配置service
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MicroMallStorageService {

    private final MicroMallConfigService configService;

    private final UploadFactory uploadFactory;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public ResponseUtil upload(MultipartFile file) {
        String type = configService.getByPropertyOrDefault(
                StorageProperties.MICROMALL_FILE_STORAGE_LOCATION, String.class,
                StorageProperties.MICROMALL_FILE_STORAGE_LOCATION.getDefaultValue());
        UploadResult result = uploadFactory.getUpload(type).upload(file);
        return ResponseUtil.ok(result);
    }
}
