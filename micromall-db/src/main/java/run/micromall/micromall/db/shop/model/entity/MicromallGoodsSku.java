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
 * 商品SKU表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallGoodsSku extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * sku自增ID
     */
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Long skuId;

    /**
     * 规格值
     */
    @TableField("specification")
    private String specification;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 库存
     */
    @TableField("number")
    private Integer number;

    /**
     * sku名称
     */
    @TableField("sku_name")
    private String skuName;

    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * sku图片
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 是否上架0下架1上架
     */
    @TableField("is_on_sale")
    private Integer isOnSale;

    /**
     * 商品重量
     */
    @TableField("weight")
    private Double weight;
}
