package run.micromall.micromall.service.system.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.system.mapper.MicroMallRoleMapper;
import run.micromall.micromall.db.system.model.entity.MicroMallRole;
import run.micromall.micromall.service.response.ResponseUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RoleService
 *
 * @author Administrator
 * @since 2021/1/19
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicroMallRoleService {
    private final MicroMallRoleMapper roleMapper;

    public ResponseUtil getRoleList(String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<String> wrapper = Wrappers.query();

        wrapper.eq(StrUtil.isNotBlank(name), "name", name);

        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", 0);
        List<MicroMallRole> roles = roleMapper.getRoleList(wrapper);
        return ResponseUtil.ok(new PageInfo<>(roles));
    }

    public Set<String> queryByIds(Long[] roleIds) {
        Set<String> roles = new HashSet<>();
        if (roleIds.length == 0) {
            return roles;
        }
        List<MicroMallRole> roleList = roleMapper.selectList(new LambdaQueryWrapper<MicroMallRole>()
                .in(MicroMallRole::getId, Arrays.asList(roleIds)).eq(MicroMallRole::getEnabled, true));
        for (MicroMallRole role : roleList) {
            roles.add(role.getName());
        }
        return roles;
    }

    public MicroMallRole findById(Long id) {
        return roleMapper.selectById(id);
    }

    public boolean checkExist(String name) {
        return roleMapper.selectCount(new LambdaQueryWrapper<MicroMallRole>().eq(MicroMallRole::getName, name)) > 0;
    }

    public int add(MicroMallRole role) {
        return roleMapper.insert(role);
    }

    public int updateById(MicroMallRole role) {
        return roleMapper.updateById(role);
    }

    public int deleteById(Long id) {
        return roleMapper.deleteById(id);
    }

    public List<MicroMallRole> queryAll() {
        return roleMapper.selectList(null);
    }
}
