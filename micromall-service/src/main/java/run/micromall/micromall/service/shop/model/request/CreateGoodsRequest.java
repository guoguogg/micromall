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
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsAttribute;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

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

    /**
     * 商品参数列表
     */
    @Valid
    private List<GoodsAttribute> attributes;

    @Data
    public static class GoodsAttribute {
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


    @Data
    public static class Goods {
        /**
         * 商品编号
         */
        @NotBlank(message = "商品编号不能为空")
        private String goodsSn;

        /**
         * 商品名称
         */
        @NotBlank(message = "商品名称不能为空")
        private String name;

        /**
         * 商品简介
         */
        @NotBlank(message = "商品简介不能为空")
        private String brief;

        /**
         * 商品详细介绍，是富文本格式
         */
        @NotBlank(message = "商品详细介绍不能为空")
        private String detail;

        /**
         * 商品页面商品图片
         */
        @NotBlank(message = "商品图片不能为空")
        private String picUrl;

        /**
         * 商品所属类目ID
         */
        @NotNull(message = "类目ID不能为空")
        @Min(value = 1, message = "categoryId必须为正整数")
        private Long categoryId;

        /**
         * 供应商id
         */
        @NotNull(message = "brandId不能为空")
        @Min(value = 1, message = "brandId必须为正整数")
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
        @Max(value = 127,message = "参数过大")
        @Min(value = 1, message = "参数过小")
        private Integer sortOrder;
    }
}
