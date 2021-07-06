package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallComment;

import java.util.List;

/**
 * <pre>
 * 评论表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
public interface MicromallCommentMapper extends BaseMapper<MicromallComment> {

    List<MicromallComment> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
