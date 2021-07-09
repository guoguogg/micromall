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
 * 规格值
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallSpecValue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 规格值自增ID
     */
    @TableId(value = "spec_value_id", type = IdType.AUTO)
    private Long specValueId;

    /**
     * 规格id
     */
    @TableField("spec_id")
    private String specId;

    /**
     * 规格值
     */
    @TableField("spec_value")
    private String specValue;
}
