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
import run.micromall.micromall.db.user.mapper.MicromallCollectMapper;
import run.micromall.micromall.db.user.model.entity.MicromallCollect;

/**
 * <pre>
 * 用户收藏表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallCollectService {

    private final MicromallCollectMapper collectMapper;

    /**
     * 保存用户收藏表
     *
     * @param micromallCollect
     * @author songhaozhi
     */
    public int insert(MicromallCollect micromallCollect) {
        return collectMapper.insert(micromallCollect);
    }

    /**
     * 通过ID修改用户收藏表
     *
     * @param micromallCollect
     * @author songhaozhi
     */
    public int updateById(MicromallCollect micromallCollect) {
        return collectMapper.updateById(micromallCollect);
    }

    /**
     * 通过ID删除用户收藏表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return collectMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询用户收藏表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallCollect selectById(Long id) {
        return collectMapper.selectById(id);
    }

    /**
     * 分页获取用户收藏表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallCollect> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallCollect> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(collectMapper.list(wrapper));
    }

}