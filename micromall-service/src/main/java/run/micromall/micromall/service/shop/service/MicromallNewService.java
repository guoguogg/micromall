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
import run.micromall.micromall.db.shop.mapper.MicromallNewMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallNew;

/**
 * <pre>
 * 新品首发商品关联 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-09
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallNewService {

    private final MicromallNewMapper micromallNewMapper;

    /**
     * 保存新品首发商品关联
     *
     * @param micromallNew
     * @author songhaozhi
     */
    public int insert(MicromallNew micromallNew) {
        return micromallNewMapper.insert(micromallNew);
    }

    /**
     * 通过ID删除新品首发商品关联
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return micromallNewMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询新品首发商品关联
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallNew selectById(Long id) {
        return micromallNewMapper.selectById(id);
    }

    /**
     * 分页获取新品首发商品关联
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallNew> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallNew> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallNewMapper.list(wrapper));
    }

}