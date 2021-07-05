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
import run.micromall.micromall.db.user.mapper.MicromallUserMapper;
import run.micromall.micromall.db.user.model.entity.MicromallUser;

/**
 * <pre>
 * 用户信息表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallUserService {

    private final MicromallUserMapper userMapper;

    /**
     * 保存用户信息表
     *
     * @author songhaozhi
     * @param micromallUser
     */
     public int insert(MicromallUser micromallUser){
         return userMapper.insert(micromallUser);
     }

    /**
     * 通过ID修改用户信息表
     *
     * @author songhaozhi
     * @param micromallUser
     */
    public int updateById(MicromallUser micromallUser){
        return userMapper.updateById(micromallUser);
    }

    /**
     * 通过ID删除用户信息表
     *
     * @author songhaozhi
     * @param id
     */
    public int deleteById(Long id){
        return userMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询用户信息表
     *
     * @author songhaozhi
     * @param id
     */
    public MicromallUser selectById(Long id){
        return userMapper.selectById(id);
    }

    /**
     * 分页获取用户信息表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallUser> list(Integer page, Integer limit, String sort, String order){
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallUser> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(userMapper.list(wrapper));
    }

}