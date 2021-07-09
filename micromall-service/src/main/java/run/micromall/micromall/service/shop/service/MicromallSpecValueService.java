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
import run.micromall.micromall.db.shop.mapper.MicromallSpecValueMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallSpecValue;

/**
 * <pre>
 * 规格值 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallSpecValueService {

    private final MicromallSpecValueMapper micromallSpecValueMapper;

    /**
     * 保存规格值
     *
     * @param micromallSpecValue
     * @author songhaozhi
     */
    public int insert(MicromallSpecValue micromallSpecValue) {
        return micromallSpecValueMapper.insert(micromallSpecValue);
    }

    /**
     * 通过ID修改规格值
     *
     * @param micromallSpecValue
     * @author songhaozhi
     */
    public int updateById(MicromallSpecValue micromallSpecValue) {
        return micromallSpecValueMapper.updateById(micromallSpecValue);
    }

    /**
     * 通过ID删除规格值
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return micromallSpecValueMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询规格值
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallSpecValue selectById(Long id) {
        return micromallSpecValueMapper.selectById(id);
    }

    /**
     * 分页获取规格值
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallSpecValue> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallSpecValue> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallSpecValueMapper.list(wrapper));
    }

}