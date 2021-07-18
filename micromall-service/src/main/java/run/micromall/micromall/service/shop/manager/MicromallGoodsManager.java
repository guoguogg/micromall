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
import run.micromall.micromall.db.shop.mapper.MicromallGoodsMapper;
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


    public MicromallGoods selectGoodsByGoodsId(Long goodsId){
        return goodsMapper.selectById(goodsId);
    }

    public int selectCountGoodsByGoodsId(Long goodsId){
        return goodsMapper.selectCount(new LambdaQueryWrapper<MicromallGoods>().eq(MicromallGoods::getGoodsId,goodsId));
    }

}
