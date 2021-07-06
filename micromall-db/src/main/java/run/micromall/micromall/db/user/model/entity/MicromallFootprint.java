package run.micromall.micromall.db.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

/**
 * <pre>
 * 用户足迹表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallFootprint extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 足迹自增ID
     */
    @TableId(value = "footprint_id", type = IdType.AUTO)
    private Long footprintId;

    /**
     * 用户表的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 浏览商品ID
     */
    @TableField("goods_id")
    private Long goodsId;
}
