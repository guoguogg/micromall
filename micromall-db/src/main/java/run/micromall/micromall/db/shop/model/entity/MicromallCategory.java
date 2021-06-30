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
 * 商品分类表
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 分类ID
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

    /**
     * 类目名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 类目介绍
     */
    @TableField("category_desc")
    private String categoryDesc;

    /**
     * 父类目ID
     */
    @TableField("pid")
    private Long pid;

    /**
     * 类目图标
     */
    @TableField("icon_url")
    private String iconUrl;

    /**
     * 类目图片
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;
}
