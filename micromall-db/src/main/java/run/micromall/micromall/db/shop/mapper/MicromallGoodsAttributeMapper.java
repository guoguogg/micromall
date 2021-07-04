package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsAttribute;

import java.util.List;

/**
 * <pre>
 * 商品属性参数 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
public interface MicromallGoodsAttributeMapper extends BaseMapper<MicromallGoodsAttribute> {

    List<MicromallGoodsAttribute> list(@Param(Constants.WRAPPER) Wrapper wrapper);

    int insertList(@Param("list")List<MicromallGoodsAttribute> list);



}
