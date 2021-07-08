package run.micromall.micromall.db.shop.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

/**
 * <pre>
 * 商品与规格关联表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallGoodsSpec extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 规格id
     */
    @TableField("spec_id")
    private Long specId;
}
