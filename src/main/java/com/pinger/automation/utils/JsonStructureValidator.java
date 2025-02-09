package com.pinger.automation.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JsonStructureValidator {

    public static boolean validateJsonStructure(String jsonFilePath, String schemaFilePath) {
        try {
            Gson gson = new Gson();

            // Load the actual JSON
            JsonObject actualJson = gson.fromJson(new FileReader(jsonFilePath), JsonObject.class);

            // Load the expected JSON schema
            JsonObject schemaJson = gson.fromJson(new FileReader(schemaFilePath), JsonObject.class);

            // Validate structure
            boolean result = compareJsonStructures(schemaJson, actualJson);
            log.info("JSON structure is valid.");

            return result;
        } catch (IOException e) {
            log.error("Error reading JSON files: " + e.getMessage());
            return false;
        }
    }

    private static boolean compareJsonStructures(JsonObject schema, JsonObject actual) {
        for (Map.Entry<String, JsonElement> entry : schema.entrySet()) {
            String key = entry.getKey();
            if (!actual.has(key)) {
                System.err.println("Missing key: " + key);
                return false;
            }

            JsonElement schemaValue = entry.getValue();
            JsonElement actualValue = actual.get(key);

            if (schemaValue.isJsonObject() && actualValue.isJsonObject()) {
                // Recursively validate nested objects
                if (!compareJsonStructures(schemaValue.getAsJsonObject(), actualValue.getAsJsonObject())) {
                    return false;
                }
            } else if (schemaValue.isJsonArray() && actualValue.isJsonArray()) {
                // Validate array elements (assuming all elements have the same structure)
                JsonArray schemaArray = schemaValue.getAsJsonArray();
                JsonArray actualArray = actualValue.getAsJsonArray();

                if (!schemaArray.isEmpty() && !actualArray.isEmpty()) {
                    if (!compareJsonStructures(schemaArray.get(0).getAsJsonObject(), actualArray.get(0).getAsJsonObject())) {
                        return false;
                    }
                }
            } else if (!schemaValue.getClass().equals(actualValue.getClass())) {
                System.err.println("Type mismatch for key: " + key);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String jsonFilePath = "src/main/resources/actual.json";
        String schemaFilePath = "src/main/resources/schemas/reportSchema";

        boolean isValid = validateJsonStructure(jsonFilePath, schemaFilePath);
        System.out.println(isValid ? "✅ JSON structure is valid!" : "JSON structure is invalid.");
    }
}
