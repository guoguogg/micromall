package run.micromall.micromall.service.shop.service;

import cn.hutool.core.util.ObjectUtil;
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
import run.micromall.micromall.db.base.ListIdRequest;
import run.micromall.micromall.db.shop.mapper.MicromallBrandMapper;
import run.micromall.micromall.db.shop.mapper.MicromallGoodsAttributeMapper;
import run.micromall.micromall.db.shop.mapper.MicromallGoodsMapper;
import run.micromall.micromall.db.shop.model.entity.MicromallBrand;
import run.micromall.micromall.db.shop.model.entity.MicromallGoods;
import run.micromall.micromall.db.shop.model.entity.MicromallGoodsAttribute;
import run.micromall.micromall.db.shop.model.entity.MicromallNew;
import run.micromall.micromall.service.shop.manager.MicromallGoodsManager;
import run.micromall.micromall.service.shop.model.request.CreateGoodsRequest;
import run.micromall.micromall.service.shop.model.request.GoodsAttribute;
import run.micromall.micromall.service.utils.BeanConvertUtils;
import run.micromall.micromall.service.utils.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    private final MicromallGoodsManager goodsManager;

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
     * @param goods
     * @author songhaozhi
     */
    public ResponseUtil updateGoods(CreateGoodsRequest goods) {
        check(goods.getGoods());
        MicromallGoods micromallGoods = new MicromallGoods();
        BeanConvertUtils.copyProperties(goods.getGoods(), micromallGoods);
        goodsMapper.updateById(micromallGoods);
        return ResponseUtil.ok();
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
        check(goods.getGoods());
        MicromallGoods micromallGoods = new MicromallGoods();
        BeanConvertUtils.copyProperties(goods.getGoods(), micromallGoods);
        goodsMapper.insert(micromallGoods);
        return micromallGoods.getGoodsId();
    }

    /**
     * 校验参数
     *
     * @param goods
     */
    private void check(CreateGoodsRequest.Goods goods) {
        if (StrUtil.isNotBlank(goods.getCategoryId())) {
            if (!goodsManager.checkCategoryId(goods.getCategoryId())) {
                throw new RuntimeException("参数错误");
            }
        }
        if (ObjectUtil.isNotNull(goods.getBrandId())) {
            if (!goodsManager.checkBrandId(goods.getBrandId())) {
                throw new RuntimeException("参数错误");
            }
        }
    }

    /**
     * 添加商品参数信息
     *
     * @param goodsAttribute
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public ResponseUtil addAttribute(GoodsAttribute goodsAttribute) {
        List<MicromallGoodsAttribute> attributeList = BeanConvertUtils.convertListTo(goodsAttribute.getAttributes()
                , MicromallGoodsAttribute::new);
        goodsAttributeMapper.insertList(attributeList);
        return ResponseUtil.ok();
    }

    /**
     * 修改商品参数信息
     *
     * @param attribute
     * @return
     */
    public ResponseUtil updateAttribute(GoodsAttribute.Attribute attribute) {
        int countGoodsByGoodsId = goodsManager.selectCountGoodsByGoodsId(attribute.getGoodsId());
        if (countGoodsByGoodsId == 0) {
            return ResponseUtil.fail(ResponseUtil.ResponseCode.PARAMETER_EXCEPTION);
        }
        MicromallGoodsAttribute goodsAttribute = new MicromallGoodsAttribute();
        BeanConvertUtils.copyProperties(attribute, goodsAttribute);
        goodsAttributeMapper.updateById(goodsAttribute);
        return ResponseUtil.ok();
    }

    /**
     * 删除商品参数
     *
     * @param request
     * @return
     */
    public ResponseUtil deleteAttribute(ListIdRequest request) {
        goodsAttributeMapper.deleteBatchIds(request.getIds());
        return ResponseUtil.ok();
    }
}