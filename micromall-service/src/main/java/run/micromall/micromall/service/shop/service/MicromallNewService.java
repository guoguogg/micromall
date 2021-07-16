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
import run.micromall.micromall.db.base.ListIdRequest;
import run.micromall.micromall.db.shop.mapper.MicromallNewMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;
import run.micromall.micromall.db.shop.model.entity.MicromallNew;
import run.micromall.micromall.service.utils.ResponseUtil;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * <pre>
 * 新品首发商品关联 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-09
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallNewService {

    private final MicromallNewMapper micromallNewMapper;

    /**
     * 保存新品首发商品关联
     *
     * @param request
     * @author songhaozhi
     */
    public ResponseUtil addMicromallNew(ListIdRequest request) {
        List<Long> ids = request.getIds();
        MicromallNew micromallNew;
        List<MicromallNew> list = CollUtil.newArrayList();
        List<MicromallNew> newList = micromallNewMapper.selectList(new LambdaQueryWrapper<MicromallNew>()
                .in(MicromallNew::getGoodsId, ids));
        for (MicromallNew aNew : newList) {
            for (Long id : ids) {
                if (aNew.getGoodsId().equals(id)) {
                    continue;
                }
                micromallNew = new MicromallNew();
                micromallNew.setGoodsId(id);
                list.add(micromallNew);
            }
        }
        micromallNewMapper.insertList(list);
        return ResponseUtil.ok();
    }

    /**
     * 通过ID删除新品首发商品关联
     *
     * @param request
     * @author songhaozhi
     */
    public ResponseUtil delete(ListIdRequest request) {
        micromallNewMapper.deleteBatchIds(request.getIds());
        return ResponseUtil.ok();
    }

    /**
     * 分页获取新品首发商品关联
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallGoods> list(String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallNew> wrapper = Wrappers.query();
        wrapper.like(StrUtil.isNullOrUndefined(name) && StrUtil.isBlank(name), "g.`name`", name);
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order),
                !"desc".equals(order), "g." + sort);
        wrapper.eq("g.deleted", false);
        wrapper.eq("n.deleted", false);
        return new PageInfo<>(micromallNewMapper.list(wrapper));
    }

}