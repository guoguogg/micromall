package run.micromall.micromall.service.system.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件工厂
 *
 * @author songhaozhi
 * @since 2021/01/16
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadFactory {

    private final Map<String, StorageUpload> uploadMap = new ConcurrentHashMap<>();

    public StorageUpload getUpload(String component) {
        StorageUpload upload = uploadMap.get(component);
        if (upload == null) {
            throw new NullPointerException("no strategy defined");
        }
        return upload;
    }

}