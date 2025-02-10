package com.pinger.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.commons.io.FileUtils.readFileToString;

@Slf4j
public final class PingerFileUtils {
    public static void ensureFileExists(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            log.error("File does not exist or is a directory: {}", path);
            throw new IllegalArgumentException("File not found: " + path);
        }
        log.info("File exists: {}", path);
    }

    public static File getFile(String path) {
        ensureFileExists(path);
        File file = new File(path);
        if (!file.canRead()) {
            log.error("File exists but cannot be read: {}", path);
            throw new IllegalStateException("File cannot be read: " + path);
        }
        log.info("Successfully retrieved file: {}", path);
        return file;
    }

    public static boolean hasJsonExtension(File file) {
        return file.getName().toLowerCase().endsWith(".json");
    }

    public static <T> T parseFileToObject(String filePath, TypeReference<T> typeReference) {
        PingerFileUtils.ensureFileExists(filePath);
        try {
            return JsonUtils.read(readFileToString(new File(filePath), Charset.defaultCharset()), typeReference);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void createJsonFileFromDto(ConfigDto dto, String directory, String fileName) {
        JsonUtils.wrightToFile(dto, directory.concat(fileName));
    }

    public static void deleteFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            if (file.exists() && file.delete()) {
                log.info("Deleted file: {}", filePath);
            } else {
                log.error("Failed to delete file or file does not exist: {}", filePath);
            }
        } catch (Exception e) {
            log.error("File does not exist.");
        }
    }
}
