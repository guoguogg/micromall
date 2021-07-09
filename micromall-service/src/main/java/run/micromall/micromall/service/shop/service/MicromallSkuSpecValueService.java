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
import run.micromall.micromall.db.shop.mapper.MicromallSkuSpecValueMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallSkuSpecValue;

/**
 * <pre>
 * SKU与规格值关联表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallSkuSpecValueService {

    private final MicromallSkuSpecValueMapper micromallSkuSpecValueMapper;

    /**
     * 保存SKU与规格值关联表
     *
     * @param micromallSkuSpecValue
     * @author songhaozhi
     */
    public int insert(MicromallSkuSpecValue micromallSkuSpecValue) {
        return micromallSkuSpecValueMapper.insert(micromallSkuSpecValue);
    }

    /**
     * 通过ID修改SKU与规格值关联表
     *
     * @param micromallSkuSpecValue
     * @author songhaozhi
     */
    public int updateById(MicromallSkuSpecValue micromallSkuSpecValue) {
        return micromallSkuSpecValueMapper.updateById(micromallSkuSpecValue);
    }

    /**
     * 通过ID删除SKU与规格值关联表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return micromallSkuSpecValueMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询SKU与规格值关联表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallSkuSpecValue selectById(Long id) {
        return micromallSkuSpecValueMapper.selectById(id);
    }

    /**
     * 分页获取SKU与规格值关联表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallSkuSpecValue> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallSkuSpecValue> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallSkuSpecValueMapper.list(wrapper));
    }

}