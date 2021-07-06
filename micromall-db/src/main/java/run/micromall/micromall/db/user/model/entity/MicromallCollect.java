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
 * 用户收藏表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallCollect extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 收藏ID
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Long collectId;

    /**
     * 用户表的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 如果type=0,则是商品ID
     */
    @TableField("value_id")
    private Integer valueId;

    /**
     * 收藏类型:如果type=0，则是商品ID
     */
    @TableField("type")
    private Integer type;
}
