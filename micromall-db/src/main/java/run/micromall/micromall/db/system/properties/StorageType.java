package run.micromall.micromall.db.system.properties;

/**
 * 存储位置类型
 *
 * @author songhaozhi
 */
public enum StorageType implements Value<Integer> {
    /**
     * 本地存储
     */
    LOCAL(0),
    ;

    private final Integer value;

    StorageType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
