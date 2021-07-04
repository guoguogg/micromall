package run.micromall.micromall.service.shop.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.micromall.micromall.db.shop.mapper.MicromallGoodsAttributeMapper;
import run.micromall.micromall.db.shop.mapper.MicromallGoodsMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsAttribute;
import run.micromall.micromall.service.shop.model.request.CreateGoodsRequest;
import run.micromall.micromall.service.utils.BeanConvertUtils;

import java.util.List;

/**
 * <pre>
 * 商品信息表 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallGoodsService {

    private final MicromallGoodsAttributeMapper goodsAttributeMapper;

    private final MicromallGoodsMapper goodsMapper;

    /**
     * 保存商品信息表
     *
     * @param micromallGoods
     * @author songhaozhi
     */
    public int insert(MicromallGoods micromallGoods) {
        return goodsMapper.insert(micromallGoods);
    }

    /**
     * 通过ID修改商品信息表
     *
     * @param micromallGoods
     * @author songhaozhi
     */
    public int updateById(MicromallGoods micromallGoods) {
        return goodsMapper.updateById(micromallGoods);
    }

    /**
     * 通过ID删除商品信息表
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return goodsMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询商品信息表
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallGoods selectById(Long id) {
        return goodsMapper.selectById(id);
    }

    /**
     * 分页获取商品信息表
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallGoods> list(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallGoods> wrapper = Wrappers.query();
        wrapper.orderBy(StrUtil.isNotBlank(sort) && StrUtil.isNotBlank(order), !"desc".equals(order), sort);
        wrapper.eq("deleted", false);
        return new PageInfo<>(goodsMapper.list(wrapper));
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public Long createGoods(CreateGoodsRequest goods) {
        MicromallGoods micromallGoods = new MicromallGoods();
        BeanConvertUtils.copyProperties(goods.getGoods(), micromallGoods);
        List<MicromallGoodsAttribute> attributeList = BeanConvertUtils.convertListTo(goods.getAttributes()
                , MicromallGoodsAttribute::new);

        goodsMapper.insert(micromallGoods);

        //todo:规格

        attributeList.forEach(attribute -> attribute.setGoodsId(micromallGoods.getGoodsId()));
        goodsAttributeMapper.insertList(attributeList);
        return micromallGoods.getGoodsId();
    }
}