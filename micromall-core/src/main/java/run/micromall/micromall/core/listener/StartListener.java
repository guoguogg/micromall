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
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import run.micromall.micromall.db.base.Constant;
import run.micromall.micromall.db.system.properties.MallProperties;
import run.micromall.micromall.db.system.properties.SmsProperties;
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
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Environment environment;

    private final MicroMallConfigService configService;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
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

        //短信服务部分
        DEFAULT_CONFIGS.put(SmsProperties.MICROMALL_ACTIVE.getValue(),
                SmsProperties.MICROMALL_ACTIVE.getDefaultValue());
        DEFAULT_CONFIGS.put(SmsProperties.MICROMALL_SMS_TEMPLATE.getValue(),
                SmsProperties.MICROMALL_SMS_TEMPLATE.getDefaultValue());
        DEFAULT_CONFIGS.put(SmsProperties.MICROMALL_ALI_SMS_ACCESS_KEY_ID.getValue(),
                SmsProperties.MICROMALL_ALI_SMS_ACCESS_KEY_ID.getDefaultValue());
        DEFAULT_CONFIGS.put(SmsProperties.MICROMALL_ALI_SMS_ACCESS_SECRET.getValue(),
                SmsProperties.MICROMALL_ALI_SMS_ACCESS_SECRET.getDefaultValue());
        DEFAULT_CONFIGS.put(SmsProperties.MICROMALL_ALI_SMS_SIGN_NAME.getValue(),
                SmsProperties.MICROMALL_ALI_SMS_SIGN_NAME.getDefaultValue());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        this.migrate();

        //读取数据库全部配置信息
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

    /**
     * 初始化sql脚本
     */
    private void migrate() {
        Flyway flyway = Flyway
                .configure()
                .locations("classpath:/db/migration")
                .dataSource(url, username, password)
                .load();
        flyway.migrate();
    }
}
