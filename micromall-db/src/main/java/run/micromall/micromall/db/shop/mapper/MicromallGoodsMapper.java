package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;

import java.util.List;

/**
 * <pre>
 * 商品信息表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
public interface MicromallGoodsMapper extends BaseMapper<MicromallGoods> {

    List<MicromallGoods> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
