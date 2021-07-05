package run.micromall.micromall.db.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.user.model.entity.MicromallFootprint;

import java.util.List;

/**
 * <pre>
 * 用户足迹表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
public interface MicromallFootprintMapper extends BaseMapper<MicromallFootprint> {

    List<MicromallFootprint> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
