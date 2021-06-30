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
import run.micromall.micromall.db.shop.mapper.MicromallIssueMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallIssue;

/**
 * <pre>
 * 常见问题表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallIssueService {

    private final MicromallIssueMapper issueMapper;

    /**
     * 保存常见问题表
     *
     * @param issue
     * @author songhaozhi
     */
    public int insert(MicromallIssue issue) {
        return issueMapper.insert(issue);
    }

    /**
     * 通过ID修改常见问题表
     *
     * @param issue
     * @author songhaozhi
     */
    public int updateById(MicromallIssue issue) {
        return issueMapper.updateById(issue);
    }

    /**
     * 通过ID删除常见问题表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return issueMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询常见问题表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallIssue selectById(Long id) {
        return issueMapper.selectById(id);
    }

    /**
     * 分页获取常见问题表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallIssue> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallIssue> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(issueMapper.list(wrapper));
    }

}