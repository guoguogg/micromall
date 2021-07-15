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
package run.micromall.micromall.service.shop.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 修改规格请求参数
 *
 * @author songhaozhi
 * @since 2021/7/15
 */
@Data
public class UpdateSpecRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 规格id
     */
    @NotNull(message = "规格ID不能为空")
    private Long specId;
    /**
     * 规格名称
     */
    @NotBlank(message = "规格名称不能为空")
    private String specName;
    /**
     * 规格值列表
     */
    @Size(min = 1, message = "规格值名称不能为空")
    private List<String> specValueList;
}
