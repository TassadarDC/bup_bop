package com.pinger.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

@Slf4j
public final class JsonUtils {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJsonString(Object object) {
        try {
            Objects.requireNonNull(object);
            return getObjectWriter().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Cannot convert object to JSON string ", e);
        }
    }

    @SneakyThrows
    public static <T> T read(String input, TypeReference<T> typeReference, Module... modules) {
        try {
            return MAPPER.readValue(input, typeReference);
        } catch (Exception e) {
            throw new JsonSyntaxException("Could not convert String to JSON object", e);
        }
    }

    public static boolean isValidJson(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Invalid file: " + (file != null ? file.getAbsolutePath() : "file is null."));
        }

        try (FileReader reader = new FileReader(file)) {
            JsonParser.parseReader(reader);
            return true; // JSON is valid
        } catch (JsonSyntaxException e) {
            log.error("Invalid JSON structure: " + e.getMessage());
        } catch (IOException e) {
            log.error("Error reading file: " + e.getMessage());
            throw new IllegalArgumentException();
        }
        return false;
    }

    public static void wrightToFile(Object object, String filePath) {
        try {
//            String jsonString = JsonUtils.toJsonString(filePath);
//            FileUtils.write(new File(filePath), jsonString, Charset.defaultCharset());




            File file = new File(filePath);
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(file, object);
            log.info("DTO saved to {}", filePath);
            log.info("File content: {}", JsonUtils.toJsonString(object));
        } catch (IOException e) {
            log.error("Failed to save DTO: {}", e.getMessage());
            throw new UncheckedIOException("Failed to save DTO: {}", e);
        }
    }

    private static ObjectWriter getObjectWriter() {
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        return MAPPER.writer();
    }
}
