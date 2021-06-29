package run.micromall.micromall.db.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.system.model.entity.MicroMallRole;

import java.util.List;

/**
 * <pre>
 * 角色表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-01-19
 */
public interface MicroMallRoleMapper extends BaseMapper<MicroMallRole> {
    /**
     * 获取角色列表
     *
     * @param wrapper
     * @return
     */
    List<MicroMallRole> getRoleList(@Param(Constants.WRAPPER) Wrapper wrapper);

}
