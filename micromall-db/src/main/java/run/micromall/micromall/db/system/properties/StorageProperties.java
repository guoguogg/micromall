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
package run.micromall.micromall.db.system.properties;

/**
 * 存储相关配置
 *
 * @author songhaozhi
 * @since 2021/1/14
 */
public enum StorageProperties implements Properties {
    /**
     * 文件存储位置
     */
    MICROMALL_FILE_STORAGE_LOCATION("micromall_file_storage_location",
            StorageType.class, StorageType.LOCAL.name()),
    /**
     * 图片压缩宽度
     */
    MICROMALL_FILE_THUMB_WIDTH("micromall_file_thumb_width",
            Integer.class, "300"),
    /**
     * 图片压缩高度
     */
    MICROMALL_FILE_THUMB_HEIGHT("micromall_file_thumb_height",
            Integer.class, "300");

    private final String value;
    private final Class<?> type;
    private final String defaultValue;

    StorageProperties(String value, Class<?> type, String defaultValue) {
        this.defaultValue = defaultValue;
        this.value = value;
        this.type = type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
