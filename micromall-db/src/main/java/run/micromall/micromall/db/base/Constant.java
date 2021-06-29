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
package run.micromall.micromall.db.base;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Constant
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
public class Constant {
    /**
     * 根目录
     */
    public static final String ROOT = System.getProperties()
            .getProperty("user.home");
    /**
     * 上传文件夹
     */
    public static final String FOLDER = "micromall" + File.separator + "upload" + File.separator;
    /**
     * 文件上传位置
     */
    public static final String UPLOAD_LOCATION = System.getProperties()
            .getProperty("user.home") + File.separator + FOLDER;
    /**
     * 所有配置都缓存在这个Map
     */
    public static Map<String, String> configMap = new HashMap<>();

    public static void setConfigMap(Map<String, String> configMap) {
        Constant.configMap = configMap;
    }
}

