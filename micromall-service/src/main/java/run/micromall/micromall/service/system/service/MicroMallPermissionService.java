package run.micromall.micromall.service.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.system.mapper.MicroMallPermissionMapper;
import run.micromall.micromall.db.system.model.entity.MicroMallPermission;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限实现类
 *
 * @author Administrator
 * @since 2021/1/19
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicroMallPermissionService {

    private final MicroMallPermissionMapper permissionMapper;

    /**
     * 检查角色是否有超级权限
     *
     * @param roleId
     * @return
     */
    public boolean checkSuperPermission(Long roleId) {
        if (roleId == null) {
            return false;
        }
        return permissionMapper.selectCount(new LambdaQueryWrapper<MicroMallPermission>()
                .eq(MicroMallPermission::getRoleId, roleId).eq(MicroMallPermission::getPermission, "*")) > 0;
    }

    public Set<String> queryByRoleId(Long roleId) {
        Set<String> permissions = new HashSet<>();
        if(roleId == null){
            return permissions;
        }
        List<MicroMallPermission> permissionList = permissionMapper.selectList(new LambdaQueryWrapper<MicroMallPermission>()
                .eq(MicroMallPermission::getRoleId, roleId));
        for(MicroMallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }
        return permissions;
    }

    public Set<String> queryByRoleIds(Long[] roleIds) {
        Set<String> permissions = new HashSet<>();
        if(roleIds.length == 0){
            return permissions;
        }
        List<MicroMallPermission> permissionList = permissionMapper.selectList(new LambdaQueryWrapper<MicroMallPermission>()
                .in(MicroMallPermission::getRoleId, Arrays.asList(roleIds)));
        for(MicroMallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }
        return permissions;
    }

    public int deleteByRoleId(Long roleId) {
        return permissionMapper.deleteById(roleId);
    }

    public int add(MicroMallPermission microMallPermission) {
        return permissionMapper.insert(microMallPermission);
    }
}
