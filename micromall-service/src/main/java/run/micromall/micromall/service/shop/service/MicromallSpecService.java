package run.micromall.micromall.service.shop.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.micromall.micromall.db.shop.mapper.MicromallSpecMapper;
import run.micromall.micromall.db.shop.mapper.MicromallSpecValueMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallSpec;
import run.micromall.micromall.db.shop.model.entity.MicromallSpecValue;
import run.micromall.micromall.service.shop.model.request.CreateSpecRequest;
import run.micromall.micromall.service.utils.ResponseUtil;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * <pre>
 * 规格表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallSpecService {

    private final MicromallSpecMapper micromallSpecMapper;
    private final MicromallSpecValueMapper specValueMapper;

    /**
     * 保存规格
     *
     * @author songhaozhi
     * @param specRequest
     */
    @Transactional(rollbackFor = Throwable.class)
     public ResponseUtil createSpec(CreateSpecRequest specRequest){
         Integer count = micromallSpecMapper.selectCount(new LambdaQueryWrapper<MicromallSpec>()
                 .eq(MicromallSpec::getSpecName, specRequest.getSpecName()));
         if(count > 0){
             return ResponseUtil.fail(ResponseUtil.ResponseCode.EXISTED,
                     String.format(ResponseUtil.ResponseCode.EXISTED.msg,"该规格名称"));
         }
         MicromallSpec spec = new MicromallSpec();
         spec.setSpecName(specRequest.getSpecName());
         micromallSpecMapper.insert(spec);
         List<String> specValueList = specRequest.getSpecValueList();
         MicromallSpecValue specValue;
         List<MicromallSpecValue> list = CollUtil.newArrayList();
         for (String value : specValueList) {
             specValue = new MicromallSpecValue();
             specValue.setSpecId(spec.getSpecId());
             specValue.setSpecValue(value);
             list.add(specValue);
         }
         specValueMapper.insertList(list);
         return ResponseUtil.ok();
     }

    /**
     * 通过ID修改规格表
     *
     * @author songhaozhi
     * @param micromallSpec
     */
    public int updateById(MicromallSpec micromallSpec){
        return micromallSpecMapper.updateById(micromallSpec);
    }

    /**
     * 通过ID删除规格表
     *
     * @author songhaozhi
     * @param id
     */
    public int deleteById(Long id){
        return micromallSpecMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询规格表
     *
     * @author songhaozhi
     * @param id
     */
    public MicromallSpec selectById(Long id){
        return micromallSpecMapper.selectById(id);
    }

    /**
     * 分页获取规格表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallSpec> list(Integer page, Integer limit, String sort, String order){
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallSpec> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallSpecMapper.list(wrapper));
    }

}