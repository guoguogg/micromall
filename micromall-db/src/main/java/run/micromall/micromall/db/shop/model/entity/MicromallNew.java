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
 * 新品首发商品关联
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallNew extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 新品首发自增ID
     */
    @TableId(value = "new_id", type = IdType.AUTO)
    private Long newId;

    /**
     * 商品ID
     */
    @TableField("goods_id")
    private Long goodsId;
}
