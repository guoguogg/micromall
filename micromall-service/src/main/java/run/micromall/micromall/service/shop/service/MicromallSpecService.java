package run.micromall.micromall.service.shop.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
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
import run.micromall.micromall.service.shop.manager.MicromallSpecValueManager;
import run.micromall.micromall.service.shop.model.request.CreateSpecRequest;
import run.micromall.micromall.service.shop.model.request.UpdateSpecRequest;
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

    private final MicromallSpecMapper specMapper;
    private final MicromallSpecValueMapper specValueMapper;
    private final MicromallSpecValueManager specValueManager;

    /**
     * 保存规格
     *
     * @param specRequest
     * @author songhaozhi
     */
    @Transactional(rollbackFor = Throwable.class)
    public ResponseUtil createSpec(CreateSpecRequest specRequest) {
        Integer count = specMapper.selectCount(new LambdaQueryWrapper<MicromallSpec>()
                .eq(MicromallSpec::getSpecName, specRequest.getSpecName()));
        if (count > 0) {
            return ResponseUtil.fail(ResponseUtil.ResponseCode.EXISTED,
                    String.format(ResponseUtil.ResponseCode.EXISTED.msg, "该规格名称"));
        }
        MicromallSpec spec = new MicromallSpec();
        spec.setSpecName(specRequest.getSpecName());
        specMapper.insert(spec);
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
     * @param updateSpecRequest
     * @author songhaozhi
     */
    @Transactional(rollbackFor = Throwable.class)
    public ResponseUtil updateSpec(UpdateSpecRequest updateSpecRequest) {
        MicromallSpec micromallSpec = specMapper.selectOne(new LambdaQueryWrapper<MicromallSpec>()
                .eq(MicromallSpec::getSpecName, updateSpecRequest.getSpecName()));
        //如果查询到规格名称存在并且规格ID不相等
        if (ObjectUtil.isNotNull(micromallSpec) && !updateSpecRequest.getSpecId().equals(micromallSpec.getSpecId())) {
            return ResponseUtil.fail(ResponseUtil.ResponseCode.EXISTED,
                    String.format(ResponseUtil.ResponseCode.EXISTED.msg, "该规格名称"));
        }
        //如果名称不一样，则修改规格名称
        if (!micromallSpec.getSpecName().equals(updateSpecRequest.getSpecName())) {
            micromallSpec.setSpecName(updateSpecRequest.getSpecName());
            specMapper.updateById(micromallSpec);
        }
        //此处我没有去做比较，然后修改或添加，而是直接删除后再添加
        specValueManager.deleteBySpecId(updateSpecRequest.getSpecId());
        List<String> specValueList = updateSpecRequest.getSpecValueList();
        MicromallSpecValue specValue;
        List<MicromallSpecValue> list = CollUtil.newArrayList();
        for (String value : specValueList) {
            specValue = new MicromallSpecValue();
            specValue.setSpecId(updateSpecRequest.getSpecId());
            specValue.setSpecValue(value);
            list.add(specValue);
        }
        specValueMapper.insertList(list);
        return ResponseUtil.ok();
    }

    /**
     * 通过ID删除规格表
     *
     * @param id
     * @author songhaozhi
     */
    @Transactional(rollbackFor = Throwable.class)
    public ResponseUtil deleteById(Long id) {
        specMapper.deleteById(id);
        specValueManager.deleteBySpecId(id);
        return ResponseUtil.ok();
    }

    /**
     * 根据ID获取查询规格表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallSpec selectById(Long id) {
        return specMapper.selectById(id);
    }

    /**
     * 分页获取规格表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallSpec> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallSpec> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(specMapper.list(wrapper));
    }

}