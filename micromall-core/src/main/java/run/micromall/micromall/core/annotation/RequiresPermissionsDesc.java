package run.micromall.micromall.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限菜单列表
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissionsDesc {
    /**
     * 菜单
     *
     * @return
     */
    String[] menu();

    /**
     * 按钮
     *
     * @return
     */
    String button();
}
