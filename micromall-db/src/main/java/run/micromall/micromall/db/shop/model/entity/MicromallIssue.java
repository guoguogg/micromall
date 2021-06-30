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
 * 常见问题表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallIssue extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 问题ID
     */
    @TableId(value = "issue_id", type = IdType.AUTO)
    private Long issueId;

    /**
     * 问题标题
     */
    @TableField("question")
    private String question;

    /**
     * 问题答案
     */
    @TableField("answer")
    private String answer;
}
