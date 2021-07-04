package run.micromall.micromall.db.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.system.model.entity.MicromallLog;

import java.util.List;

/**
 * <pre>
 * 日志记录 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
public interface MicromallLogMapper extends BaseMapper<MicromallLog> {

    List<MicromallLog> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
