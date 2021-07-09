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
import run.micromall.micromall.db.shop.mapper.MicromallGoodsSpecMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsSpec;

/**
 * <pre>
 * 商品与规格关联表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallGoodsSpecService {

    private final MicromallGoodsSpecMapper micromallGoodsSpecMapper;

    /**
     * 保存商品与规格关联表
     *
     * @param micromallGoodsSpec
     * @author songhaozhi
     */
    public int insert(MicromallGoodsSpec micromallGoodsSpec) {
        return micromallGoodsSpecMapper.insert(micromallGoodsSpec);
    }

    /**
     * 通过ID修改商品与规格关联表
     *
     * @param micromallGoodsSpec
     * @author songhaozhi
     */
    public int updateById(MicromallGoodsSpec micromallGoodsSpec) {
        return micromallGoodsSpecMapper.updateById(micromallGoodsSpec);
    }

    /**
     * 通过ID删除商品与规格关联表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return micromallGoodsSpecMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询商品与规格关联表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallGoodsSpec selectById(Long id) {
        return micromallGoodsSpecMapper.selectById(id);
    }

    /**
     * 分页获取商品与规格关联表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallGoodsSpec> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallGoodsSpec> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallGoodsSpecMapper.list(wrapper));
    }

}