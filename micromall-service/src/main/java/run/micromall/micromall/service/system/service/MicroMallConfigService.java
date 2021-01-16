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
import org.springframework.util.Assert;
import run.micromall.micromall.db.base.Constant;
import run.micromall.micromall.db.system.mapper.MicroMallConfigMapper;
import run.micromall.micromall.db.system.model.MicroMallConfig;
import run.micromall.micromall.db.system.properties.Properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 系统配置service
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
@Service
public interface MicroMallConfigService {

    /**
     * 添加配置
     *
     * @param key
     * @param value
     * @return
     */
    int addConfig(String key, String value);

    /**
     * 通过Properties查询
     *
     * @param properties
     * @return
     */
    Optional<Object> getByProperties(Properties properties);

    <T> T getByPropertyOrDefault(Properties properties, Class<T> propertyType, T defaultValue);

    <T> Optional<T> getByProperties(Properties properties, Class<T> propertyType);
    /**
     * 通过key查询配置
     *
     * @param key
     * @return
     */
    Optional<Object> getByKey(String key);

    /**
     * 查询所有配置
     *
     * @return map
     */
    Map<String, String> selectMap();
}
