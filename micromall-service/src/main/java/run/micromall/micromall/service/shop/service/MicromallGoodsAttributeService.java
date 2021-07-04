package run.micromall.micromall.service.shop.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.shop.mapper.MicromallGoodsAttributeMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsAttribute;

/**
 * <pre>
 * 商品属性参数 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallGoodsAttributeService {

    private final MicromallGoodsAttributeMapper goodsAttributeMapper;

    /**
     * 保存商品属性参数
     *
     * @author songhaozhi
     * @param goodsAttribute
     */
     public int insert(MicromallGoodsAttribute goodsAttribute){
         return goodsAttributeMapper.insert(goodsAttribute);
     }

    /**
     * 通过ID修改商品属性参数
     *
     * @author songhaozhi
     * @param goodsAttribute
     */
    public int updateById(MicromallGoodsAttribute goodsAttribute){
        return goodsAttributeMapper.updateById(goodsAttribute);
    }

    /**
     * 通过ID删除商品属性参数
     *
     * @author songhaozhi
     * @param id
     */
    public int deleteById(Long id){
        return goodsAttributeMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询商品属性参数
     *
     * @author songhaozhi
     * @param id
     */
    public MicromallGoodsAttribute selectById(Long id){
        return goodsAttributeMapper.selectById(id);
    }

    /**
     * 分页获取商品属性参数
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallGoodsAttribute> list(Integer page, Integer limit, String sort, String order){
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallGoodsAttribute> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(goodsAttributeMapper.list(wrapper));
    }

}