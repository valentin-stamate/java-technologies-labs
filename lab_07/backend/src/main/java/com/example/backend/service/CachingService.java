package com.example.backend.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CachingService {

    private Map<String, byte[]> fileCache;

    @PostConstruct
    public void init() {
        fileCache = new HashMap<>();
    }

    public void addFile(String fileId, byte[] buffer) {
        System.out.printf("[CachingService] Add file %s", fileId);
        fileCache.put(fileId, buffer);
    }

    public void removeFile(String fileId) {
        System.out.printf("[CachingService] Removing file %s", fileId);

        if (fileCache.remove(fileId) != null) {
            System.out.println("removed");
        } else {
            System.out.println("not found");
        }
    }

    public byte[] getFile(String fileId) {
        System.out.printf("[CachingService] Geting file %s", fileId);

        byte[] buffer = fileCache.get(fileId);

        if (buffer != null) {
            System.out.println("found");
        } else {
            System.out.println("not found");
        }

        return buffer;
    }
}
