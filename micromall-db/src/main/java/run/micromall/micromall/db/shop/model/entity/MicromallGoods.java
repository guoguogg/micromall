package run.micromall.micromall.db.shop.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

import java.math.BigDecimal;

/**
 * <pre>
 * 商品信息表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallGoods extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Long goodsId;

    /**
     * 商品编号
     */
    @TableField("goods_sn")
    private String goodsSn;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品简介
     */
    @TableField("brief")
    private String brief;

    /**
     * 商品详细介绍，是富文本格式
     */
    @TableField("detail")
    private String detail;

    /**
     * 商品页面商品图片
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 商品所属类目ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 供应商id
     */
    @TableField("brand_id")
    private Long brandId;

    /**
     * 是否上架
     */
    @TableField("is_on_sale")
    private Boolean isOnSale;

    /**
     * 商品单位，例如件、盒
     */
    @TableField("unit")
    private String unit;

    /**
     * 专柜价格
     */
    @TableField("counter_price")
    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    @TableField("retail_price")
    private BigDecimal retailPrice;

    /**
     * 可修改销量
     */
    @TableField("sold")
    private Integer sold;

    /**
     * 商品真销量
     */
    @TableField("sale")
    private Integer sale;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;
}
