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
 * 评论表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallComment extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 评论ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 如果type=0,则是商品评论
     */
    @TableField("value_id")
    private Long valueId;

    /**
     * 订单商品id
     */
    @TableField("order_goods_id")
    private Long orderGoodsId;

    /**
     * 评论类型:如果type=0,则是商品评论
     */
    @TableField("type")
    private Integer type;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 管理员回复内容
     */
    @TableField("admin_content")
    private String adminContent;

    /**
     * 用户表的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 是否含有图片
     */
    @TableField("has_picture")
    private Boolean hasPicture;

    /**
     * 图片地址列表: 英文逗号分隔
     */
    @TableField("pic_urls")
    private String picUrls;

    /**
     * 评分， 1-5
     */
    @TableField("star")
    private Integer star;
}
