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
package run.micromall.micromall.db.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import run.micromall.micromall.db.base.BaseEntity;

import java.io.Serializable;

/**
 * 系统配置表
 *
 * @author songhaozhi
 * @since 2021/1/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("micromall_config")
public class MicroMallConfig extends BaseEntity implements Serializable {
    /**
     * 自增ID
     */
    @TableId(value = "sys_id", type = IdType.AUTO)
    private Long sysId;
    /**
     * key
     */
    @TableField(value = "key_name")
    private String keyName;
    /**
     * value
     */
    @TableField(value = "key_value")
    private String keyValue;
}
