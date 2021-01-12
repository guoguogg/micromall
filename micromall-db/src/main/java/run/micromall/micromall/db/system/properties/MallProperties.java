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
 * 商城相关配置
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
public enum MallProperties implements Properties {

    MICROMALL_MALL_NAME("micromall_mall_name", String.class, "微商城");

    private final String value;
    private final Class<?> type;
    private final String defaultValue;

    MallProperties(String value, Class<?> type, String defaultValue) {
        this.defaultValue = defaultValue;
        if (!isSupportedType(type)) {
            throw new IllegalArgumentException("不支持该类型:" + type);
        }
        this.value = value;
        this.type = type;
    }

    static boolean isSupportedType(Class<?> type) {
        if (type == null) {
            return false;
        }
        return type.isAssignableFrom(String.class)
                || type.isAssignableFrom(Number.class)
                || type.isAssignableFrom(Integer.class)
                || type.isAssignableFrom(Long.class)
                || type.isAssignableFrom(Boolean.class)
                || type.isAssignableFrom(Short.class)
                || type.isAssignableFrom(Byte.class)
                || type.isAssignableFrom(Double.class)
                || type.isAssignableFrom(Float.class)
                || type.isAssignableFrom(Enum.class);
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
