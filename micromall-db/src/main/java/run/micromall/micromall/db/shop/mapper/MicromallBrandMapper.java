package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallBrand;

import java.util.List;

/**
 * <pre>
 * 品牌商 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
public interface MicromallBrandMapper extends BaseMapper<MicromallBrand> {

    List<MicromallBrand> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
