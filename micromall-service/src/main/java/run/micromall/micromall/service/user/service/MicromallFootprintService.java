package run.micromall.micromall.service.user.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.user.mapper.MicromallFootprintMapper;
import run.micromall.micromall.db.user.model.entity.MicromallFootprint;

/**
 * <pre>
 * 用户足迹表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallFootprintService {

    private final MicromallFootprintMapper footprintMapper;

    /**
     * 保存用户足迹表
     *
     * @author songhaozhi
     * @param micromallFootprint
     */
     public int insert(MicromallFootprint micromallFootprint){
         return footprintMapper.insert(micromallFootprint);
     }

    /**
     * 通过ID修改用户足迹表
     *
     * @author songhaozhi
     * @param micromallFootprint
     */
    public int updateById(MicromallFootprint micromallFootprint){
        return footprintMapper.updateById(micromallFootprint);
    }

    /**
     * 通过ID删除用户足迹表
     *
     * @author songhaozhi
     * @param id
     */
    public int deleteById(Long id){
        return footprintMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询用户足迹表
     *
     * @author songhaozhi
     * @param id
     */
    public MicromallFootprint selectById(Long id){
        return footprintMapper.selectById(id);
    }

    /**
     * 分页获取用户足迹表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallFootprint> list(Integer page, Integer limit, String sort, String order){
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallFootprint> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(footprintMapper.list(wrapper));
    }

}