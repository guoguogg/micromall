package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsSpec;

import java.util.List;

/**
 * <pre>
 * 商品与规格关联表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
public interface MicromallGoodsSpecMapper extends BaseMapper<MicromallGoodsSpec> {

    List<MicromallGoodsSpec> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
