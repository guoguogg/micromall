package run.micromall.micromall.service.system.storage;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UploadResult {
    private String filename;

    private String filePath;

    private String key;

    private String thumbPath;

    private String suffix;

    private String mediaType;

    private Long size;
}