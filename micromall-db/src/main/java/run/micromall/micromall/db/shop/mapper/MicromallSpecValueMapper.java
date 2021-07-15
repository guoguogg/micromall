package run.micromall.micromall.db.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import run.micromall.micromall.db.shop.model.entity.MicromallSpecValue;

import java.util.List;

/**
 * <pre>
 * 规格值 Mapper 接口
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
public interface MicromallSpecValueMapper extends BaseMapper<MicromallSpecValue> {

    List<MicromallSpecValue> list(@Param(Constants.WRAPPER) Wrapper wrapper);

    int insertList(@Param("list")List<MicromallSpecValue> list);



}
