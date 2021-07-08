package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallSpec;

import java.util.List;

/**
 * <pre>
 * 规格表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
public interface MicromallSpecMapper extends BaseMapper<MicromallSpec> {

    List<MicromallSpec> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
