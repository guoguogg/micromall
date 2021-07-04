package run.micromall.micromall.core.annotation;

import run.micromall.micromall.db.system.model.enums.LogTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录日志注解
 *
 * @author songhaozhi
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 操作接口名称
     *
     * @return
     */
    String name() default "";

    /**
     * 日志类型，默认为系统日志
     *
     * @return
     */
    LogTypeEnum logType() default LogTypeEnum.SYSTEM;

    /**
     * 是否记录到数据库
     */
    boolean save() default true;
}
