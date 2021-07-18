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
package run.micromall.micromall.service.shop.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.shop.mapper.MicromallBrandMapper;
import run.micromall.micromall.db.shop.mapper.MicromallCategoryMapper;
import run.micromall.micromall.db.shop.mapper.MicromallGoodsMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallBrand;
import run.micromall.micromall.db.shop.model.entity.MicromallCategory;
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;

/**
 * MicromallGoodsManager
 *
 * @author songhaozhi
 * @since 2021/7/18
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallGoodsManager {


    private final MicromallGoodsMapper goodsMapper;

    private final MicromallCategoryMapper categoryMapper;

    private final MicromallBrandMapper brandMapper;

    public MicromallGoods selectGoodsByGoodsId(Long goodsId) {
        return goodsMapper.selectById(goodsId);
    }

    public int selectCountGoodsByGoodsId(Long goodsId) {
        return goodsMapper.selectCount(new LambdaQueryWrapper<MicromallGoods>().eq(MicromallGoods::getGoodsId, goodsId));
    }

    /**
     * 检查分类ID是否存在
     *
     * @param categoryId 分类ID串
     * @return 如果传入的分类ID全部存在返回true，否则返回false
     */
    public boolean checkCategoryId(String categoryId) {
        String[] categoryIds = categoryId.split(",");
        if (categoryIds.length == 0) {
            return false;
        }
        Integer count = categoryMapper.selectCount(new LambdaQueryWrapper<MicromallCategory>()
                .in(MicromallCategory::getCategoryId, categoryIds));
        if (categoryIds.length == count) {
            return true;
        }
        return false;
    }

    /**
     * 检查供应商ID是否存在
     *
     * @param brandId
     * @return 存在返回true
     */
    public boolean checkBrandId(Long brandId) {
        return brandMapper.selectCount(new LambdaQueryWrapper<MicromallBrand>()
                .eq(MicromallBrand::getBrandId, brandId)) > 0;
    }

}
