package run.micromall.micromall.db.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.system.model.entity.MicroMallPermission;

import java.util.List;

/**
 * <pre>
 * 权限表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-01-19
 */
public interface MicroMallPermissionMapper extends BaseMapper<MicroMallPermission> {

    List<MicroMallPermission> getPermissionPageList(@Param(Constants.WRAPPER) Wrapper wrapper);

}
