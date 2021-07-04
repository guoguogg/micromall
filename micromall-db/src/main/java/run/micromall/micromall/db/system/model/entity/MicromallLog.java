package run.micromall.micromall.db.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;
import run.micromall.micromall.db.system.model.enums.LogTypeEnum;


/**
 * <pre>
 * 日志记录
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 请求路径
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @TableField("param")
    private String param;

    /**
     * 操作接口名称
     */
    @TableField("name")
    private String name;

    /**
     * 操作用户的ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 请求用户id
     */
    @TableField("id")
    private Long id;

    /**
     * 操作用户的user_agent
     */
    @TableField("ua")
    private String ua;

    /**
     * 浏览器类型
     */
    @TableField("os")
    private String os;

    /**
     * 请求来源地址
     */
    @TableField("referer")
    private String referer;

    /**
     * 日志类型（系统操作日志，用户访问日志，异常记录日志）
     */
    @TableField("type")
    private LogTypeEnum type;
}
