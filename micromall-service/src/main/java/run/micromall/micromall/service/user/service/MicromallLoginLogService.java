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
import run.micromall.micromall.db.user.mapper.MicromallLoginLogMapper;
import run.micromall.micromall.db.user.model.entity.MicromallLoginLog;

/**
 * <pre>
 * 用户登录日志记录 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallLoginLogService {

    private final MicromallLoginLogMapper loginLogMapper;

    /**
     * 保存用户登录日志记录
     *
     * @param micromallLoginLog
     * @author songhaozhi
     */
    public int insert(MicromallLoginLog micromallLoginLog) {
        return loginLogMapper.insert(micromallLoginLog);
    }

    /**
     * 通过ID修改用户登录日志记录
     *
     * @param micromallLoginLog
     * @author songhaozhi
     */
    public int updateById(MicromallLoginLog micromallLoginLog) {
        return loginLogMapper.updateById(micromallLoginLog);
    }

    /**
     * 通过ID删除用户登录日志记录
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return loginLogMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询用户登录日志记录
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallLoginLog selectById(Long id) {
        return loginLogMapper.selectById(id);
    }

    /**
     * 分页获取用户登录日志记录
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallLoginLog> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallLoginLog> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(loginLogMapper.list(wrapper));
    }

}