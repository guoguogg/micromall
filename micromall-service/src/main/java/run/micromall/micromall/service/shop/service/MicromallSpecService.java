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
import run.micromall.micromall.db.shop.mapper.MicromallSpecMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallSpec;

/**
 * <pre>
 * 规格表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallSpecService {

    private final MicromallSpecMapper micromallSpecMapper;

    /**
     * 保存规格表
     *
     * @author songhaozhi
     * @param micromallSpec
     */
     public int insert(MicromallSpec micromallSpec){
         return micromallSpecMapper.insert(micromallSpec);
     }

    /**
     * 通过ID修改规格表
     *
     * @author songhaozhi
     * @param micromallSpec
     */
    public int updateById(MicromallSpec micromallSpec){
        return micromallSpecMapper.updateById(micromallSpec);
    }

    /**
     * 通过ID删除规格表
     *
     * @author songhaozhi
     * @param id
     */
    public int deleteById(Long id){
        return micromallSpecMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询规格表
     *
     * @author songhaozhi
     * @param id
     */
    public MicromallSpec selectById(Long id){
        return micromallSpecMapper.selectById(id);
    }

    /**
     * 分页获取规格表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallSpec> list(Integer page, Integer limit, String sort, String order){
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallSpec> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallSpecMapper.list(wrapper));
    }

}