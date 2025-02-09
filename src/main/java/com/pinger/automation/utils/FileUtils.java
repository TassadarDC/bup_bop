package com.pinger.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public final class FileUtils {
    public static void checkIfFileExists(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            log.error("File does not exist or is a directory: {}", path);
            throw new IllegalArgumentException("File not found: " + path);
        }
        log.info("File exists: {}", path);
    }

    public static File getFile(String path) {
        checkIfFileExists(path);
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

    @SneakyThrows
    public static <T> T parseFileToObject(String filePath, TypeReference<T> typeReference) {
        FileUtils.checkIfFileExists(filePath);
        // Parse json into DTO
        return JsonUtils.read(readFileContent(filePath), typeReference);
    }

    private static String readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return content.toString().trim();
    }

    //todo update to use Object as type for dto - need to update createFileFromDto to createFileFromJson - or create separate method.
    // And after this, change dto type to Object and use JsonUtils.toJsonString(dto)
    public static void createJsonFileFromDto(ConfigDto dto, String directory, String fileName) {
        JsonUtils.createFileFromDto(dto, directory.concat(fileName));
    }

    public static void deleteFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            if (file.exists() && file.delete()) {
                log.info("Deleted file: {}", filePath);
            } else {
                log.warn("Failed to delete file or file does not exist: {}", filePath);
            }
        } catch (Exception e) {
            log.error("File does not exist.");
        }
    }
}
