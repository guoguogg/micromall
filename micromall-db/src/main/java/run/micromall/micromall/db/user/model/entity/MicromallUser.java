package run.micromall.micromall.db.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;

import java.time.LocalDateTime;


/**
 * <pre>
 * 用户信息表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallUser extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 性别：0 未知， 1男， 1 女
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDateTime birthday;

    /**
     * 用户手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 微信小程序miniopenid
     */
    @TableField("weixin_mini_openid")
    private String weixinMiniOpenid;

    /**
     * 微信登录openid
     */
    @TableField("weixin_openid")
    private String weixinOpenid;

    /**
     * 微信登录unionid
     */
    @TableField("weixin_unionid")
    private String weixinUnionid;

    /**
     * 用户头像图片
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 0 可用, 1 禁用, 2 注销
     */
    @TableField("status")
    private Integer status;
}
