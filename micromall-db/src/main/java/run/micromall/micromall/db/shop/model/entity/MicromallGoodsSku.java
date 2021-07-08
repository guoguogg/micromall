package run.micromall.micromall.db.shop.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

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
     * 商品id
     */

    @TableField("goods_id")
    private Long goodsId;
}
