package run.micromall.micromall.db.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <pre>
 * 管理员表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-01-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("micromall_admin")
public class MicroMallAdmin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */

    @TableField("username")
    @Pattern(regexp = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$",message = "参数错误")
    private String username;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 管理员密码
     */
    @TableField("password")
    private String password;

    /**
     * 最近一次登录IP地址
     */
    @TableField("last_login_ip")
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 头像图片
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 角色列表
     */
    @TableField("role_ids")
    private Long[] roleIds;
}
