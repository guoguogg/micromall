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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import run.micromall.micromall.db.validation.AddGroup;
import run.micromall.micromall.db.validation.UpdateGroup;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 创建商品请求参数
 *
 * @author songhaozhi
 * @since 2021/7/4
 */
@Data
public class CreateGoodsRequest {
    /**
     * 商品信息
     */
    @Valid
    @NotNull(message = "参数错误")
    private Goods goods;

    @Data
    public static class Goods {
        /**
         * 商品ID
         */
        @NotNull(groups = {UpdateGroup.class}, message = "商品ID不能为空")
        private Long goodsId;
        /**
         * 商品编号
         */
        @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "商品编号不能为空")
        private String goodsSn;

        /**
         * 商品名称
         */
        @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "商品名称不能为空")
        private String name;

        /**
         * 商品简介
         */
        @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "商品简介不能为空")
        private String brief;

        /**
         * 商品详细介绍，是富文本格式
         */
        @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "商品详细介绍不能为空")
        private String detail;

        /**
         * 商品页面商品图片
         */
        @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "商品图片不能为空")
        private String picUrl;

        /**
         * 商品所属类目ID, ","分隔
         */
        private String categoryId;

        /**
         * 供应商id
         */
        private Long brandId;

        /**
         * 商品单位，例如件、盒
         */
        private String unit;

        /**
         * 专柜价格
         */
        private BigDecimal counterPrice;

        /**
         * 零售价格
         */
        private BigDecimal retailPrice;

        /**
         * 可修改销量
         */
        private Integer sold;
        /**
         * 排序
         */
        @Max(value = 127, message = "参数过大")
        @Min(value = 1, message = "参数过小")
        private Integer sortOrder;
    }
}
