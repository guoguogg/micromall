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

import lombok.Data;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Id列表请求参数
 *
 * @author songhaozhi
 * @since 2021/7/16
 */
@Data
public class ListIdRequest implements Serializable {
    /**
     * ID列表
     */
    @Size(min = 1, message = "参数错误")
    private List<Long> ids;
}
