package run.micromall.micromall.service.system.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UploadFactory {

    @Autowired
    Map<String, StorageUpload> uploadMap = new ConcurrentHashMap<>();

    public StorageUpload getUpload(String component) {
        StorageUpload upload = uploadMap.get(component);
        if (upload == null) {
            throw new NullPointerException("no strategy defined");
        }
        return upload;
    }

}