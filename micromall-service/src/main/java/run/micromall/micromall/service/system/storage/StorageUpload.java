package run.micromall.micromall.service.system.storage;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 *
 * @author songhaozhi
 */
public interface StorageUpload {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    UploadResult upload(@NonNull MultipartFile file);
    /**
     * 通过key删除文件
     *
     * @param key
     */
    void delete(String key);
}
