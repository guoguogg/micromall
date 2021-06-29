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

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import run.micromall.micromall.db.base.Constant;
import run.micromall.micromall.db.system.properties.StorageProperties;
import run.micromall.micromall.service.system.service.MicroMallConfigService;
import run.micromall.micromall.service.utils.FileUtil;
import run.micromall.micromall.service.utils.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

    private final MicroMallConfigService configService;

    private static final String THUMBNAIL_SUFFIX = "-thumbnail";

    @Value("${micromall.domain}")
    private String domain;

    @Override
    public UploadResult upload(MultipartFile file) {
        log.debug("上传文件至本地。。。");
        UploadResult result = new UploadResult();
        try {
            String originalFilename = file.getOriginalFilename();
            String folder = DateUtil.thisYear() + File.separator + (DateUtil.thisMonth() + 1) + File.separator;
            String location = Constant.UPLOAD_LOCATION + folder;
            log.debug("location:{}", location);
            Path rootLocation = Paths.get(location);
            Files.createDirectories(rootLocation);
            String suffix = originalFilename.substring(originalFilename.indexOf("."));
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + suffix;
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName));
            Thumbnails.of(file.getInputStream()).size(
                    configService.getByPropertyOrDefault(StorageProperties.MICROMALL_FILE_THUMB_WIDTH, Integer.class, 300),
                    configService.getByPropertyOrDefault(StorageProperties.MICROMALL_FILE_THUMB_HEIGHT, Integer.class, 300))
                    .keepAspectRatio(false).toFile(new File(location + uuid + "_small" + suffix));

            result.setFileName(fileName);
            result.setMediaType(file.getContentType());
            result.setSuffix(suffix);
            result.setFilePath(domain + "upload/" + folder.replace("\\", "/") + fileName);
            result.setThumbUrl(domain + "upload/" + folder.replace("\\", "/") + uuid + "_small" + suffix);
            result.setSize(FileUtil.parseSize(file.getSize()));
        } catch (IOException e) {
            log.error("上传文件失败", e);
            throw new RuntimeException("上传文件失败");
        }
        return result;
    }

    @Override
    public void delete(String key) {
        if(StrUtil.isBlank(key)){
            throw new IllegalArgumentException("参数异常");
        }
        Path path = Paths.get(Constant.ROOT + key);
        log.debug(path.toString());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("附件 " + key + " 删除失败", e);
        }
        String basename = FilenameUtils.getBasename(key);
        String extension = FilenameUtils.getExtension(key);
        // 获取缩略图名称
        String thumbnailName = basename + THUMBNAIL_SUFFIX + '.' + extension;

        // 获取缩略图路径
        Path thumbnailPath = Paths.get(path.getParent().toString(), thumbnailName);

        // 删除略缩图
        try {
            boolean deleteResult = Files.deleteIfExists(thumbnailPath);
            if (!deleteResult) {
                log.warn("Thumbnail: [{}] may not exist", thumbnailPath.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("附件缩略图 " + thumbnailName + " 删除失败", e);
        }
    }
}
