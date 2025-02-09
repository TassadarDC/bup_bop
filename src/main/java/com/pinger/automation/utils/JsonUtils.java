package com.pinger.automation.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pinger.automation.core.model.CustomException;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public final class JsonUtils {
    public static String toJsonString(Object object) {
        try {
            Objects.requireNonNull(object);
            return getObjectWriter().writeValueAsString(object);
        } catch (Throwable t) {
            throw new CustomException("Cannot convert object to JSON string ", t);
        }
    }

    @SneakyThrows
    public static <T> T read(String input, TypeReference<T> typeReference, Module... modules) {
        final ObjectMapper mapper = getObjectMapper(modules);
        try {
            return mapper.readValue(input, typeReference);
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
            System.err.println("Invalid JSON structure: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    public static File createFileFromDto(InputDataDto dto, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, dto);
            log.info("DTO saved to {}", filePath);
            log.info("File content: {}", JsonUtils.toJsonString(dto));
            return file;
        } catch (IOException e) {
            log.error("Failed to save DTO: {}", e.getMessage());
            //todo check exception
            throw new CustomException("Failed to save DTO: {}", e);
        }
    }

    private static ObjectWriter getObjectWriter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        return mapper.writer();
    }

    private static synchronized ObjectMapper getObjectMapper(Module... modules) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.registerModules(modules);
        return mapper;
    }
}
