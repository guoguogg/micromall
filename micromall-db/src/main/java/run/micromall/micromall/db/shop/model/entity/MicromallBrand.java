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
 * 品牌商
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-06-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MicromallBrand extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 品牌商ID
     */
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    /**
     * 品牌商名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 品牌商简介
     */
    @TableField("brand_desc")
    private String brandDesc;

    /**
     * 品牌商页的品牌商图片
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;
}
