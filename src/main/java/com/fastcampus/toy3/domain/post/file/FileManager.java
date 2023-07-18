package com.fastcampus.toy3.domain.post.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileManager {

    @Value("${file.dir}")
    private String fileDir;

    public UploadedFile storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty())
            return null;

        String uploadedName = file.getOriginalFilename();
        String storedName = getStoredName(uploadedName);
        String fullPath = getFullPath(storedName);
        file.transferTo(new File(fullPath));
        return new UploadedFile(uploadedName, storedName);
    }

    public String getFullPath(String storedName) {
        return fileDir + storedName;
    }

    private String getStoredName(String filename) {
        String ext = getExtension(filename);
        String uuid = UUID.randomUUID().toString();
        return uuid + '.' + ext;
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
