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
 * 规格表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallSpec extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 规格自增id
     */
    @TableId(value = "spec_id", type = IdType.AUTO)
    private Long specId;

    /**
     * 规格名称
     */
    @TableField("spec_name")
    private String specName;
}
