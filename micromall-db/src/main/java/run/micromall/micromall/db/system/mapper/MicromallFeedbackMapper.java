package run.micromall.micromall.db.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.system.model.entity.MicromallFeedback;

import java.util.List;

/**
 * <pre>
 * 意见反馈表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
public interface MicromallFeedbackMapper extends BaseMapper<MicromallFeedback> {

    List<MicromallFeedback> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
