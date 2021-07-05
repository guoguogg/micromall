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
 * 用户登录日志记录
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallLoginLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 登录日志主键id
     */
    @TableId(value = "login_log_id", type = IdType.AUTO)
    private Long loginLogId;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 登录IP地址
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 登录地址
     */
    @TableField("login_address")
    private String loginAddress;
}
