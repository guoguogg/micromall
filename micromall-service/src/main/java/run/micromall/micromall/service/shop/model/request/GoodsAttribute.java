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

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import run.micromall.micromall.db.validation.UpdateGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * GoodsAttribute
 *
 * @author songhaozhi
 * @since 2021/7/18
 */
@Data
public class GoodsAttribute {

    /**
     * 商品参数列表
     */
    @Valid
    private List<Attribute> attributes;

    @Data
    public static class Attribute {
        /**
         * 商品表的商品ID
         */
        @NotNull(message = "商品ID不能为空")
        private Long goodsId;

        /**
         * 商品参数名称
         */
        @NotBlank(message = "参数名称不能为空")
        private String attribute;

        /**
         * 商品参数值
         */
        @NotBlank(message = "参数值不能为空")
        private String value;
    }

}
