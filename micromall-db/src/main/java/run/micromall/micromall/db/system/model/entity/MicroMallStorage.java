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
 * 文件存储表对象
 *
 * @author songhaozhi
 * @since 2021/1/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("micromall_storage")
public class MicroMallStorage extends BaseEntity implements Serializable {
    /**
     * 自增ID
     */
    @TableId(value = "storage_id", type = IdType.AUTO)
    private Long storageId;
    /**
     * key
     */
    @TableField(value = "`key`")
    private String key;
    /**
     * 附件名称
     */
    @TableField(value = "file_name")
    private String fileName;
    /**
     * 原图访问路径
     */
    @TableField(value = "file_path")
    private String filePath;
    /**
     * 略缩图路径
     */
    @TableField(value = "thumb_url")
    private String thumbUrl;
    /**
     * 文件类型
     */
    @TableField(value = "media_type")
    private String mediaType;
    /**
     * 文件后缀
     */
    @TableField(value = "suffix")
    private String suffix;
    /**
     * 文件大小
     */
    @TableField(value = "size")
    private String size;
    /**
     * 存储位置
     */
    @TableField(value = "type")
    private String type;
}
