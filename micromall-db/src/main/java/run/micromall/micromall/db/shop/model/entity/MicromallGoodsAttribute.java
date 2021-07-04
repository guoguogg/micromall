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
 * 商品属性参数
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallGoodsAttribute extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "attribute_id", type = IdType.AUTO)
    private Long attributeId;

    /**
     * 商品表的商品ID
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品参数名称
     */
    @TableField("attribute")
    private String attribute;

    /**
     * 商品参数值
     */
    @TableField("`value`")
    private String value;
}
