package run.micromall.micromall.service.system.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.system.mapper.MicroMallAdminMapper;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;

import java.time.LocalDateTime;
import java.util.List;

/**
 * AdminService
 *
 * @author Administrator
 * @since 2021/1/19
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicroMallAdminService {

    private final MicroMallAdminMapper adminMapper;

    public List<MicroMallAdmin> findAdmin(String username) {
        return adminMapper.selectByUsername(username);
    }

    public int updateById(MicroMallAdmin admin) {
        return adminMapper.updateById(admin,admin.getId());
    }

    public int updateLastLoginIpAndLastLoginTimeById(String updatedLastLoginIp, LocalDateTime updatedLastLoginTime,Long id) {
        return adminMapper.updateLastLoginIpAndLastLoginTimeById(updatedLastLoginIp,updatedLastLoginTime,id);
    }

    public PageInfo<MicroMallAdmin> list(String username, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);

        QueryWrapper<MicroMallAdmin> wrapper = Wrappers.query();

        wrapper.eq(StrUtil.isNotBlank(username),"username", username);

        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(adminMapper.list(wrapper));
    }

    public List<MicroMallAdmin> all() {
        return adminMapper.list(null);
    }

    public int add(MicroMallAdmin admin) {
        return adminMapper.insertSelective(admin);
    }

    public MicroMallAdmin findById(Long id) {
        return adminMapper.selectById(id);
    }

    public int deleteById(Long id) {
        return adminMapper.deleteById(id);
    }
}
