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
package run.micromall.micromall.service.system.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import run.micromall.micromall.db.system.mapper.MicroMallStorageMapper;

/**
 * 本地文件上传
 *
 * @author songhaozhi
 * @since 2021/1/14
 */
@Component("LOCAL")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LocalStorage implements StorageUpload {

    private final MicroMallStorageMapper storageMapper;

    @Override
    public UploadResult upload(MultipartFile file) {
        log.debug("上传文件至本地。。。");
        return null;
    }

}
