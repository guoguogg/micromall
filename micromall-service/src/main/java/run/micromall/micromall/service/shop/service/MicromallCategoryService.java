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
import run.micromall.micromall.db.shop.mapper.MicromallCategoryMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallCategory;

/**
 * <pre>
 * 商品分类表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallCategoryService {

    private final MicromallCategoryMapper categoryMapper;

    /**
     * 保存商品分类表
     *
     * @param category
     * @author songhaozhi
     */
    public int insert(MicromallCategory category) {
        return categoryMapper.insert(category);
    }

    /**
     * 通过ID修改商品分类表
     *
     * @param category
     * @author songhaozhi
     */
    public int updateById(MicromallCategory category) {
        return categoryMapper.updateById(category);
    }

    /**
     * 通过ID删除商品分类表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return categoryMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询商品分类表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallCategory selectById(Long id) {
        return categoryMapper.selectById(id);
    }

    /**
     * 分页获取商品分类表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallCategory> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallCategory> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(categoryMapper.list(wrapper));
    }

}