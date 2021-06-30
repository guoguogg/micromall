package run.micromall.micromall.db.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.system.model.entity.MicroMallAdmin;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <pre>
 * 管理员表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-01-19
 */
public interface MicroMallAdminMapper extends BaseMapper<MicroMallAdmin> {

    List<MicroMallAdmin> list(@Param(Constants.WRAPPER) Wrapper wrapper);

    int insertSelective(MicroMallAdmin microMallAdmin);

    List<MicroMallAdmin> selectByUsername(@Param("username") String username);

    int updateLastLoginIpAndLastLoginTimeById(@Param("updatedLastLoginIp") String updatedLastLoginIp, @Param("updatedLastLoginTime") LocalDateTime updatedLastLoginTime, @Param("id") Long id);

    MicroMallAdmin selectById(@Param("id") Long id);

    int updateById(@Param("updated") MicroMallAdmin updated, @Param("id") Long id);

}
