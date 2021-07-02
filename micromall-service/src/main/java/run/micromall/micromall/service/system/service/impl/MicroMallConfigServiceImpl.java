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
package run.micromall.micromall.service.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.base.Constant;
import run.micromall.micromall.db.system.mapper.MicroMallConfigMapper;
import run.micromall.micromall.db.system.model.entity.MicroMallConfig;
import run.micromall.micromall.db.system.properties.Properties;
import run.micromall.micromall.service.system.service.MicroMallConfigService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 系统配置service
 * <p>
 * 代码参考halo项目
 *
 * @author songhaozhi
 * @link https://github.com/halo-dev/halo/blob/master/src/main/java/run/halo/app/service/impl/OptionServiceImpl.java
 * @since 2021/1/12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MicroMallConfigServiceImpl implements MicroMallConfigService {

    private final MicroMallConfigMapper configMapper;

    @Override
    public void save(Map<String, String> map) {
        if (!map.isEmpty()) {
            map.forEach(this::saveOption);
        }
    }

    @Override
    public void saveOption(String key, String value) {
        if (StrUtil.isNotEmpty(key)) {
            List<MicroMallConfig> list = configMapper.selectList(new LambdaQueryWrapper<MicroMallConfig>()
                    .eq(MicroMallConfig::getKeyName, key));
            if (list.isEmpty()) {
                MicroMallConfig config = new MicroMallConfig();
                config.setKeyName(key);
                config.setKeyValue(value);
                configMapper.insert(config);
            } else {
                MicroMallConfig config = new MicroMallConfig();
                config.setKeyName(list.get(0).getKeyName());
                config.setKeyValue(value);
                config.setSysId(list.get(0).getSysId());
                configMapper.updateById(config);
            }
        }
    }

    @Override
    public int addConfig(String key, String value) {
        MicroMallConfig config = new MicroMallConfig();
        config.setKeyName(key);
        config.setKeyValue(value);
        return configMapper.insert(config);
    }

    @Override
    public Optional<Object> getByProperties(Properties properties) {
        return getByKey(properties.getValue());
    }

    @Override
    public <T> Optional<T> getByProperties(Properties properties, Class<T> propertyType) {
        return getByProperties(properties).map(propertyValue -> Properties.convertTo(propertyValue.toString(), propertyType));
    }

    @Override
    public <T> T getByPropertyOrDefault(Properties properties, Class<T> propertyType, T defaultValue) {
        return getByProperties(properties, propertyType).orElse(defaultValue);
    }

    @Override
    public Optional<Object> getByKey(String key) {
        return Optional.ofNullable(Constant.configMap.get(key));
    }

    @Override
    public Map<String, String> selectMap() {
        List<MicroMallConfig> microMallConfigs = configMapper.selectList(null);
        Map<String, String> systemConfigs = new HashMap<>();
        for (MicroMallConfig config : microMallConfigs) {
            systemConfigs.put(config.getKeyName(), config.getKeyValue());
        }
        return systemConfigs;
    }
}
