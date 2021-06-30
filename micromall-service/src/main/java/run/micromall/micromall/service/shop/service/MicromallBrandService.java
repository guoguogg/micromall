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
import run.micromall.micromall.db.shop.mapper.MicromallBrandMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallBrand;

/**
 * <pre>
 * 品牌商 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallBrandService {

    private final MicromallBrandMapper brandMapper;

    /**
     * 保存品牌商
     *
     * @param brand
     * @author songhaozhi
     */
    public int insert(MicromallBrand brand) {
        return brandMapper.insert(brand);
    }

    /**
     * 通过ID修改品牌商
     *
     * @param brand
     * @author songhaozhi
     */
    public int updateById(MicromallBrand brand) {
        return brandMapper.updateById(brand);
    }

    /**
     * 通过ID删除品牌商
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return brandMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询品牌商
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallBrand selectById(Long id) {
        return brandMapper.selectById(id);
    }

    /**
     * 分页获取品牌商
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     * @author songhaozhi
     */
    public PageInfo<MicromallBrand> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallBrand> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(brandMapper.list(wrapper));
    }

}