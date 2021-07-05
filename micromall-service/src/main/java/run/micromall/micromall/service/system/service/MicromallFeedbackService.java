package run.micromall.micromall.service.system.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.system.mapper.MicromallFeedbackMapper;
import run.micromall.micromall.db.system.model.entity.MicromallFeedback;

/**
 * <pre>
 * 意见反馈表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallFeedbackService {

    private final MicromallFeedbackMapper feedbackMapper;

    /**
     * 保存意见反馈表
     *
     * @param micromallFeedback
     * @author songhaozhi
     */
    public int insert(MicromallFeedback micromallFeedback) {
        return feedbackMapper.insert(micromallFeedback);
    }

    /**
     * 通过ID修改意见反馈表
     *
     * @param micromallFeedback
     * @author songhaozhi
     */
    public int updateById(MicromallFeedback micromallFeedback) {
        return feedbackMapper.updateById(micromallFeedback);
    }

    /**
     * 通过ID删除意见反馈表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return feedbackMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询意见反馈表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallFeedback selectById(Long id) {
        return feedbackMapper.selectById(id);
    }

    /**
     * 分页获取意见反馈表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallFeedback> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallFeedback> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(feedbackMapper.list(wrapper));
    }

}