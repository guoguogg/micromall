package run.micromall.micromall.db.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import run.micromall.micromall.db.base.BaseEntity;
import run.micromall.micromall.db.validation.AddGroup;
import run.micromall.micromall.db.validation.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * <pre>
 * 角色表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-01-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("micromall_role")
public class MicroMallRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {UpdateGroup.class}, message = "参数错误")
    private Long id;

    /**
     * 角色名称
     */
    @TableField("`name`")
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "参数错误")
    private String name;

    /**
     * 角色描述
     */
    @TableField("`desc`")
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "参数错误")
    private String desc;

    /**
     * 是否启用
     */
    @TableField("enabled")
    private Boolean enabled;
}
