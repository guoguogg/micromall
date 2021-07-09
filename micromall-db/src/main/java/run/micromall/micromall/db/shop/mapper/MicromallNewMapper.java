package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallNew;

import java.util.List;

/**
 * <pre>
 * 新品首发商品关联 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-09
 */
public interface MicromallNewMapper extends BaseMapper<MicromallNew> {

    List<MicromallNew> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
