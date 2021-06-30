package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallCategory;

import java.util.List;

/**
 * <pre>
 * 商品分类表 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
public interface MicromallCategoryMapper extends BaseMapper<MicromallCategory> {

    List<MicromallCategory> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
