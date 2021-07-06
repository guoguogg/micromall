package run.micromall.micromall.db.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

/**
 * <pre>
 * 意见反馈表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallFeedback extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 反馈ID
     */
    @TableId(value = "feedback_id", type = IdType.AUTO)
    private Long feedbackId;

    /**
     * 用户表的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户名称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 反馈类型
     */
    @TableField("feed_type")
    private String feedType;

    /**
     * 反馈内容
     */
    @TableField("content")
    private String content;

    /**
     * 状态0未处理1已处理
     */
    @TableField("status")
    private Integer status;

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
}
