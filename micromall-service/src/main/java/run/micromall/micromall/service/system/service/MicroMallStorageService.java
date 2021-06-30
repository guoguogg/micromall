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

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import run.micromall.micromall.db.base.BaseEntity;
import run.micromall.micromall.db.system.mapper.MicroMallStorageMapper;
import run.micromall.micromall.db.system.model.entity.MicroMallStorage;
import run.micromall.micromall.db.system.properties.StorageProperties;
import run.micromall.micromall.service.system.storage.UploadFactory;
import run.micromall.micromall.service.system.storage.UploadResult;
import run.micromall.micromall.service.utils.ResponseUtil;

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

    private final MicroMallStorageMapper storageMapper;

    private final UploadFactory uploadFactory;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public UploadResult upload(MultipartFile file) {
        String type = configService.getByPropertyOrDefault(
                StorageProperties.MICROMALL_FILE_STORAGE_LOCATION, String.class,
                StorageProperties.MICROMALL_FILE_STORAGE_LOCATION.getDefaultValue());
        UploadResult result = uploadFactory.getUpload(type).upload(file);
        MicroMallStorage storage = new MicroMallStorage();
        BeanUtils.copyProperties(result, storage);
        storageMapper.insert(storage);
        return result;
    }
    /**
     * 附件分页列表
     *
     * @param name
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<MicroMallStorage> list(String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        LambdaQueryWrapper<MicroMallStorage> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StrUtil.isNotBlank(name), MicroMallStorage::getFileName, name);
        wrapper.orderByDesc(BaseEntity::getAddTime);
        return new PageInfo<>(storageMapper.selectList(wrapper));
    }

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    public MicroMallStorage delete(Long id) {
        MicroMallStorage storage = storageMapper.selectById(id);
        if (storageMapper.deleteById(id) > 0) {
            uploadFactory.getUpload(storage.getType()).delete(storage.getKey());
        }
        return storage;
    }
}
