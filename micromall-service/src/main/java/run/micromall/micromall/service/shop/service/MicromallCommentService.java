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
import run.micromall.micromall.db.shop.mapper.MicromallCommentMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallComment;

/**
 * <pre>
 * 评论表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallCommentService {

    private final MicromallCommentMapper commentMapper;

    /**
     * 保存评论表
     *
     * @author songhaozhi
     * @param micromallComment
     */
     public int insert(MicromallComment micromallComment){
         return commentMapper.insert(micromallComment);
     }

    /**
     * 通过ID修改评论表
     *
     * @author songhaozhi
     * @param micromallComment
     */
    public int updateById(MicromallComment micromallComment){
        return commentMapper.updateById(micromallComment);
    }

    /**
     * 通过ID删除评论表
     *
     * @author songhaozhi
     * @param id
     */
    public int deleteById(Long id){
        return commentMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询评论表
     *
     * @author songhaozhi
     * @param id
     */
    public MicromallComment selectById(Long id){
        return commentMapper.selectById(id);
    }

    /**
     * 分页获取评论表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallComment> list(Integer page, Integer limit, String sort, String order){
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallComment> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(commentMapper.list(wrapper));
    }

}