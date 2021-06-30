package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallIssue;

import java.util.List;

/**
 * <pre>
 * 常见问题表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
public interface MicromallIssueMapper extends BaseMapper<MicromallIssue> {

    List<MicromallIssue> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
