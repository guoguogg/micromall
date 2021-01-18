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
package run.micromall.micromall.core.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import run.micromall.micromall.db.base.Constant;
import run.micromall.micromall.db.system.properties.MallProperties;
import run.micromall.micromall.db.system.properties.StorageProperties;
import run.micromall.micromall.service.system.service.MicroMallConfigService;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目启动监听
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StartListener  implements ApplicationListener<ContextRefreshedEvent> {

    private final Environment environment;

    private final MicroMallConfigService configService;
    /**
     * 默认配置
     */
    private final static Map<String, String> DEFAULT_CONFIGS = new HashMap<>();

    static {
        DEFAULT_CONFIGS.put(MallProperties.MICROMALL_MALL_NAME.getValue(),
                MallProperties.MICROMALL_MALL_NAME.getDefaultValue());
        //附件部分
        DEFAULT_CONFIGS.put(StorageProperties.MICROMALL_FILE_STORAGE_LOCATION.getValue(),
                StorageProperties.MICROMALL_FILE_STORAGE_LOCATION.getDefaultValue());
        DEFAULT_CONFIGS.put(StorageProperties.MICROMALL_FILE_THUMB_WIDTH.getValue(),
                StorageProperties.MICROMALL_FILE_THUMB_WIDTH.getDefaultValue());
        DEFAULT_CONFIGS.put(StorageProperties.MICROMALL_FILE_THUMB_HEIGHT.getValue(),
                StorageProperties.MICROMALL_FILE_THUMB_HEIGHT.getDefaultValue());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 1. 读取数据库全部配置信息
        Map<String, String> configs = configService.selectMap();
        for (Map.Entry<String, String> entry : DEFAULT_CONFIGS.entrySet()) {
            if (configs.containsKey(entry.getKey())) {
                continue;
            }
            configs.put(entry.getKey(), entry.getValue());
            configService.addConfig(entry.getKey(), entry.getValue());
        }
        //todo:这里可以用Redis缓存
        Constant.setConfigMap(configs);
    }
}
