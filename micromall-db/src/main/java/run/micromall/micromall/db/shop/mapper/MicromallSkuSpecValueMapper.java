package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallSkuSpecValue;

import java.util.List;

/**
 * <pre>
 * SKU与规格值关联表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
public interface MicromallSkuSpecValueMapper extends BaseMapper<MicromallSkuSpecValue> {

    List<MicromallSkuSpecValue> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
