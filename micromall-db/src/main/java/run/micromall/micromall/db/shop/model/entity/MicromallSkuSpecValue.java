package run.micromall.micromall.db.shop.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

/**
 * <pre>
 * SKU与规格值关联表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallSkuSpecValue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * skuId
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 规格值ID
     */
    @TableField("spec_value_id")
    private Long specValueId;
}
