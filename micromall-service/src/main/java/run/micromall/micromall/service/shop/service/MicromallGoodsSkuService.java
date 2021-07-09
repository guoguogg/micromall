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
import run.micromall.micromall.db.shop.mapper.MicromallGoodsSkuMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsSku;

/**
 * <pre>
 * 商品SKU表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallGoodsSkuService {

    private final MicromallGoodsSkuMapper micromallGoodsSkuMapper;

    /**
     * 保存商品SKU表
     *
     * @param micromallGoodsSku
     * @author songhaozhi
     */
    public int insert(MicromallGoodsSku micromallGoodsSku) {
        return micromallGoodsSkuMapper.insert(micromallGoodsSku);
    }

    /**
     * 通过ID修改商品SKU表
     *
     * @param micromallGoodsSku
     * @author songhaozhi
     */
    public int updateById(MicromallGoodsSku micromallGoodsSku) {
        return micromallGoodsSkuMapper.updateById(micromallGoodsSku);
    }

    /**
     * 通过ID删除商品SKU表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return micromallGoodsSkuMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询商品SKU表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallGoodsSku selectById(Long id) {
        return micromallGoodsSkuMapper.selectById(id);
    }

    /**
     * 分页获取商品SKU表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallGoodsSku> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallGoodsSku> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallGoodsSkuMapper.list(wrapper));
    }

}