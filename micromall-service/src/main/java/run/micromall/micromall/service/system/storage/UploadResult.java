package run.micromall.micromall.service.system.storage;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UploadResult {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;

    private String key;
    /**
     * 略缩图路径
     */
    private String thumbUrl;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 文件类型
     */
    private String mediaType;
    /**
     * 文件大小
     */
    private String size;
}