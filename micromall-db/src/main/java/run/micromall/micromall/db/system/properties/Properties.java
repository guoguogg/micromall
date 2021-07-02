package run.micromall.micromall.db.system.properties;

import org.springframework.util.ObjectUtils;

/**
 * 配置项接口
 * <p>
 * 代码参考halo项目
 *
 * @link https://github.com/halo-dev/halo/blob/master/src/main/java/run/halo/app/model/properties/PropertyEnum.java
 */
public interface Properties extends Value<String> {
    /**
     * 获取配置类型
     *
     * @return
     */
    Class<?> getType();

    /**
     * 获取默认值
     *
     * @return
     */
    String getDefaultValue();

    static <T> T convertTo(String value, Class<T> type) {
        if (ObjectUtils.isEmpty(value) || ObjectUtils.isEmpty(type)) {
            throw new RuntimeException("参数错误");
        }
        if (type.isAssignableFrom(String.class)) {
            return (T) value;
        }
        if (type.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(value);
        }

        if (type.isAssignableFrom(Long.class)) {
            return (T) Long.valueOf(value);
        }

        if (type.isAssignableFrom(Boolean.class)) {
            return (T) Boolean.valueOf(value);
        }

        if (type.isAssignableFrom(Short.class)) {
            return (T) Short.valueOf(value);
        }

        if (type.isAssignableFrom(Byte.class)) {
            return (T) Byte.valueOf(value);
        }

        if (type.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(value);
        }

        if (type.isAssignableFrom(Float.class)) {
            return (T) Float.valueOf(value);
        }
        throw new UnsupportedOperationException("Unsupported convention for blog property type:" + type.getName() + " provided");
    }
}
