package run.micromall.micromall.db.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.user.model.entity.MicromallCollect;

import java.util.List;

/**
 * <pre>
 * 用户收藏表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
public interface MicromallCollectMapper extends BaseMapper<MicromallCollect> {

    List<MicromallCollect> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
